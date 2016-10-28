package com.police.dao;

import com.police.model.LogEvent;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/24.
 */
public interface LogRepository extends PagingAndSortingRepository<LogEvent,Integer>{

}
