package org.melonframework.mvc.type;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czm 2020/11/12.
 *
 * 存储处理完后的结果数据，以及显示数据的视图
 */
public class ModelAndView {
    //页面所在的路径
    @Getter
    private String view;
    //页面的data数据
    @Getter
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }

    //链式调用设置内容：modelAndView.setView("addheadline.jsp").addViewData("aaa", "bbb");
    public ModelAndView addViewData(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this;
    }
}
