package org.melonframework.mvc.render.impl;

import org.melonframework.mvc.RequestProcessorChain;
import org.melonframework.mvc.render.ResultRender;
import org.melonframework.mvc.type.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author czm 2020/11/8.
 *
 * 页面渲染器
 */
public class ViewResultRender implements ResultRender {

    public static final String VIEW_PATH = "/templates/";
    private ModelAndView modelAndView;

    public ViewResultRender(Object mv) {
        if (mv instanceof ModelAndView) {
            //1、如果入参类型是ModeAndView，则直接赋值给成员变量
            this.modelAndView = (ModelAndView)mv;
        } else if (mv instanceof String) {
            //2、如果入参类型是String，则为视图，需要包装后才赋值给成员变量
            this.modelAndView = new ModelAndView().setView((String)mv);
        } else {
            //3、针对其它情况，则直接抛出异常
            throw new RuntimeException("Illegal request result type");
        }
    }

    /**
     * 将请求处理结果按照视图路径转发至对应视图进行展示
     * @param requestProcessorChain
     * @throws Exception
     */
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        HttpServletRequest request = requestProcessorChain.getRequest();
        HttpServletResponse response = requestProcessorChain.getResponse();
        String path = modelAndView.getView();
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        //JSP
        request.getRequestDispatcher(VIEW_PATH + path).forward(request, response);
    }
}
