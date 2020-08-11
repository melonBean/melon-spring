package com.melon.entity.dto;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @author czm 2020/8/11.
 */
@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
