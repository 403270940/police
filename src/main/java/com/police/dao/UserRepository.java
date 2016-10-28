package com.police.dao;

import com.police.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/23.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer>{
    User save(User user);
    User findByPhone(String phone);
}
