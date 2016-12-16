package com.police.dao;

import com.police.model.AdminUser;
import com.police.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/23.
 */
public interface AdminUserRepository extends PagingAndSortingRepository<AdminUser,Integer>{
    AdminUser save(AdminUser user);
    AdminUser findByPhone(String phone);
    AdminUser findById(int id);
}
