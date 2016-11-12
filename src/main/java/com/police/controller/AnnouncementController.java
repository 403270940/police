package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liyy on 16/10/24.
 */
@Controller
@RequestMapping("/api")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @RequestMapping(value = "announcement")
    @ResponseBody
    public BaseResponse getAnnouncement(){
        return announcementService.getAnnouncement();
    }

}
