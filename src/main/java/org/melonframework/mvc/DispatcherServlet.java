package org.melonframework.mvc;

import lombok.extern.slf4j.Slf4j;
import org.melonframework.aop.AspectWeaver;
import org.melonframework.core.BeanContainer;
import org.melonframework.inject.DependencyInjector;
import org.melonframework.mvc.processor.RequestProcessor;
import org.melonframework.mvc.processor.impl.ControllerRequestProcessor;
import org.melonframework.mvc.processor.impl.JspRequestProcessor;
import org.melonframework.mvc.processor.impl.PreRequestProcessor;
import org.melonframework.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author czm 2020/8/11.
 *
 * / 不会对 .jsp请求拦截
 * /* 会对 .jsp请求拦截
 * 在 tomcat 配置文件中可以看到相应的配置原则，/ 匹配优先级低于 /* 类型
 */
@Slf4j
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    List<RequestProcessor> PROCESSOR = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        //1、初始化容器
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.melon");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();

        //2、初始化请求处理器责任链
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor(this.getServletContext()));
        PROCESSOR.add(new JspRequestProcessor(this.getServletContext()));
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        //1、创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSOR.iterator(), req, rep);

        //2、通过责任链模式来依次调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();

        //3、对处理结果进行渲染
        requestProcessorChain.doRender();
    }
}
