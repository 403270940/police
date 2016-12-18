package com.police.dao;

import com.police.model.Announcement;
import com.police.model.Captcha;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liyy on 16/10/27.
 */
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement,Integer> {
    List<Announcement> findAll();
    Announcement findById(int id);
}
