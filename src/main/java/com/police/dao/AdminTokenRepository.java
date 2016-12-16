package com.police.dao;

import com.police.model.AdminToken;
import com.police.model.LogInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/24.
 */
public interface AdminTokenRepository extends PagingAndSortingRepository<AdminToken,Integer>{
    AdminToken findByUid(int uid);
    AdminToken findByToken(String token);
    AdminToken findByUidAndToken(int id, String token);

}
