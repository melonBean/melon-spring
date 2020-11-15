package org.melonframework.mvc.processor;

import org.melonframework.mvc.RequestProcessorChain;

/**
 * @author czm 2020/11/8.
 *
 * 请求执行器
 */
public interface RequestProcessor {

    boolean process(RequestProcessorChain requestProcessorChain) throws Exception;

}
