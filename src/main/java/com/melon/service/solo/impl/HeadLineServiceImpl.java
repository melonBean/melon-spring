package com.melon.service.solo.impl;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.dto.Result;
import com.melon.service.solo.HeadLineService;
import org.melonframework.core.annotation.Service;

import java.util.List;

/**
 * @author czm 2020/8/11.
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
