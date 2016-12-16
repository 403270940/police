package com.police.service;

import com.police.dao.AdminTokenRepository;
import com.police.dao.LogInfoRepository;
import com.police.model.AdminToken;
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
    @Autowired
    AdminTokenRepository adminTokenRepository;
    public boolean exists(String token){
        LogInfo logInfo = logInfoRepository.findByToken(token);
        return logInfo != null;
    }
    public boolean existsAdminToken(String token){
        AdminToken adminToken = adminTokenRepository.findByToken(token);
        return adminToken != null;
    }
}
