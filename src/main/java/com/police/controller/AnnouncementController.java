package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.AnnouncementService;
import com.police.service.LoginService;
import com.police.service.ThemeService;
import com.police.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
