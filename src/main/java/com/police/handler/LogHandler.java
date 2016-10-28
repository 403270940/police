package com.police.handler;

import com.google.common.eventbus.Subscribe;
import com.police.model.LogEvent;
import com.police.dao.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyy on 16/10/24.
 */
@Service
public class LogHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogRepository logRepository;

    @Subscribe
    public void handle(LogEvent logEvent){
        logRepository.save(logEvent);
    }
}
