package com.melon.service.combine.impl;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.bo.ShopCategory;
import com.melon.entity.dto.MainPageInfoDTO;
import com.melon.entity.dto.Result;
import com.melon.service.combine.HeadLineShopCategoryCombineService;
import com.melon.service.solo.HeadLineService;
import com.melon.service.solo.ShopCategoryService;
import org.melonframework.core.annotation.Service;
import org.melonframework.inject.annotation.Autowired;

import java.util.List;

/**
 * @author czm 2020/8/11.
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 1. 获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);

        // 2. 获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 10);

        // 3. 合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(headLineResult, shopCategoryResult);

        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
