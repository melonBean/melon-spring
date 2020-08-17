package com.melon.controller.frontend;

import com.melon.entity.dto.MainPageInfoDTO;
import com.melon.entity.dto.Result;
import com.melon.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.melonframework.core.annotation.Controller;
import org.melonframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author czm 2020/8/11.
 */
@Controller
@Getter
public class MainPageController {

    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest request, HttpServletResponse response) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

}
