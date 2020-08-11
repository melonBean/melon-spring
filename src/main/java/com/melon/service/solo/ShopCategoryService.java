package com.melon.service.solo;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.bo.ShopCategory;
import com.melon.entity.dto.Result;

import java.util.List;

/**
 * @author czm 2020/8/11.
 */
public interface ShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<HeadLine> queryShopCategoryById(int shopCategoryId);
    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
