package com.police.service;

import com.police.dao.LogRepository;
import com.police.model.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyy on 16/10/24.
 */
@Service
public class LogEventService {

    @Autowired
    LogRepository logRepository;
    public void recieve(LogEvent logEvent){
        logRepository.save(logEvent);
    }
}
