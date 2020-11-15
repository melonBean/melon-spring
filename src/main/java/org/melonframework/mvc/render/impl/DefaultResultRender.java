package org.melonframework.mvc.render.impl;

import org.melonframework.mvc.RequestProcessorChain;
import org.melonframework.mvc.render.ResultRender;

/**
 * @author czm 2020/11/8.
 *
 * 默认渲染器
 */
public class DefaultResultRender implements ResultRender {
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().setStatus(requestProcessorChain.getResponseCode());
    }
}
