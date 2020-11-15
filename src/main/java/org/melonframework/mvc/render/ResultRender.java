package org.melonframework.mvc.render;

import org.melonframework.mvc.RequestProcessorChain;

/**
 * @author czm 2020/11/8.
 *
 * 渲染请求结果
 */
public interface ResultRender {

    //执行渲染
    void render(RequestProcessorChain requestProcessorChain) throws Exception;

}
