package com.police.service;

import com.police.dao.LogInfoRepository;
import com.police.model.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyy on 16/11/5.
 */
@Service
public class TokenService {

    @Autowired
    LogInfoRepository logInfoRepository;
    public boolean exists(String token){
        LogInfo logInfo = logInfoRepository.findByToken(token);
        return logInfo != null;
    }
}
