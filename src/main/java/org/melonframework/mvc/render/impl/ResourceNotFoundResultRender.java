package org.melonframework.mvc.render.impl;

import org.melonframework.mvc.RequestProcessorChain;
import org.melonframework.mvc.render.ResultRender;

import javax.servlet.http.HttpServletResponse;

/**
 * @author czm 2020/11/8.
 *
 * 资源找不到时使用的渲染器
 */
public class ResourceNotFoundResultRender implements ResultRender {

    private String httpMethod;
    private String httpPath;

    public ResourceNotFoundResultRender(String requestMethod, String requestPath) {
        this.httpMethod = requestMethod;
        this.httpPath = requestPath;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND,
                "获取不到对应的请求资源：请求路径[" + httpPath + "] 请求方法[" + httpMethod + "]");
    }
}
