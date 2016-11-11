package com.police.service;

import com.police.dao.AnnouncementRepository;
import com.police.model.Announcement;
import com.police.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyy on 16/11/7.
 */
@Service
public class AnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
    /*
    **  @return code: int 0成功
    *           msg : String
    *           data:
    *               id:String
     */
    public BaseResponse getAnnouncement() {
        List<Announcement> announcementList = announcementRepository.findAll();
        BaseResponse announcement = new BaseResponse(0,"成功",announcementList);
        return announcement;
    }
}
