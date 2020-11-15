package org.melonframework.mvc.processor.impl;

import org.melonframework.mvc.RequestProcessorChain;
import org.melonframework.mvc.processor.RequestProcessor;

/**
 * @author czm 2020/11/8.
 *
 * 请求预处理，包括编码以及路径处理
 */
public class PreRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        return false;
    }
}
