package com.melon.service.combine;

import com.melon.entity.dto.MainPageInfoDTO;
import com.melon.entity.dto.Result;

/**
 * @author czm 2020/8/11.
 */
public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
