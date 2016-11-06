package com.police.dao;

import com.police.model.LogInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/24.
 */
public interface LogInfoRepository extends PagingAndSortingRepository<LogInfo,Integer>{
    LogInfo findByUid(int uid);
    LogInfo findByToken(String token);
}
