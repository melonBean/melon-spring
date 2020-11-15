package org.melonframework.mvc.render.impl;

import org.melonframework.mvc.RequestProcessorChain;
import org.melonframework.mvc.render.ResultRender;

import javax.servlet.http.HttpServletResponse;

/**
 * @author czm 2020/11/8.
 *
 * 内部异常渲染器
 */
public class InternalErrorResultRender implements ResultRender {
    private String errorMsg;
    public InternalErrorResultRender(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
}
