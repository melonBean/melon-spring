package com.melon.service.solo;

import com.melon.entity.bo.HeadLine;
import com.melon.entity.dto.Result;

import java.util.List;

/**
 * @author czm 2020/8/11.
 */
public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
