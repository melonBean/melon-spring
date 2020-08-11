package com.melon.controller.superadmin;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.bo.ShopCategory;
import com.melon.entity.dto.Result;
import com.melon.service.solo.HeadLineService;
import com.melon.service.solo.ShopCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author czm 2020/8/11.
 */
public class ShopCategoryOperationController {

    private ShopCategoryService ShopCategoryService;

    public Result<Boolean> addShopCategory(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return ShopCategoryService.addShopCategory(new ShopCategory());
    }

    public Result<Boolean> removeShopCategory(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return ShopCategoryService.removeShopCategory(1);
    }

    public Result<Boolean> modifyShopCategory(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return ShopCategoryService.modifyShopCategory(new ShopCategory());
    }

    public Result<HeadLine> queryShopCategoryById(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return ShopCategoryService.queryShopCategoryById(1);
    }

    public Result<List<ShopCategory>> queryShopCategory(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return ShopCategoryService.queryShopCategory(new ShopCategory(), 1, 5);
    }

}
