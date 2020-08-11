package com.melon.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author czm 2020/8/11.
 */
@Data
public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
