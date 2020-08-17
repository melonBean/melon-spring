package com.melon.controller.superadmin;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.dto.Result;
import com.melon.service.solo.HeadLineService;
import org.melonframework.core.annotation.Controller;
import org.melonframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author czm 2020/8/11.
 */
@Controller
public class HeadLineOperationController {

    @Autowired
    private HeadLineService headLineService;

    public Result<Boolean> addHeadLine(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return headLineService.addHeadLine(new HeadLine());
    }

    public Result<Boolean> removeHeadLine(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return headLineService.removeHeadLine(1);
    }

    public Result<Boolean> modifyHeadLine(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }

    public Result<List<HeadLine>> queryHeadLine(HttpServletRequest request, HttpServletResponse response) {
        // TODO：参数检验以及请求参数转化
        return headLineService.queryHeadLine(new HeadLine(), 1, 5);
    }

}
