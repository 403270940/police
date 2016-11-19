package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by liyy on 16/10/24.
 */
@Controller
@RequestMapping("/api")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping(value = "image", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse uploadImage(@RequestParam(value = "uid",defaultValue = "") String uid,
                                    @RequestParam(value = "location",defaultValue = "") String location,
                                    @RequestParam(value = "createTime",defaultValue = "") String createTime,
                                    @RequestParam(value = "image",defaultValue = "") MultipartFile image,
                                    @RequestParam(value = "comment",defaultValue = "") String comment) throws Exception{
        return uploadService.uploadImage(uid,location,createTime,image,comment);
    }

    @RequestMapping(value = "video",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse uploadVideo(@RequestParam(value = "uid",defaultValue = "") String uid,
                                    @RequestParam(value = "location",defaultValue = "") String location,
                                    @RequestParam(value = "createTime",defaultValue = "") String createTime,
                                    @RequestParam(value = "video",defaultValue = "") MultipartFile video,
                                    @RequestParam(value = "comment",defaultValue = "") String comment) throws Exception{
        return uploadService.uploadVideo(uid, location, createTime, video, comment);

    }

    @RequestMapping(value = "image/summary")
    @ResponseBody
    public BaseResponse getImageSummary(@RequestParam(value = "uid",defaultValue = "") String uid){
        return uploadService.getImageSummary(uid);
    }


    @RequestMapping(value = "image/detail",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getImageDetail(@RequestParam(value = "uid",defaultValue = "") String uid,
                                        @RequestParam(value = "id",defaultValue = "") String id) throws Exception{
        return uploadService.getImageDetail(uid, id);

    }

    @RequestMapping(value = "video/summary")
    @ResponseBody
    public BaseResponse getVideoSummary(@RequestParam(value = "uid",defaultValue = "") String uid){
        return uploadService.getVideoSummary(uid);
    }


    @RequestMapping(value = "video/detail",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getVideoDetail(@RequestParam(value = "uid",defaultValue = "") String uid,
                                       @RequestParam(value = "id",defaultValue = "") String id) throws Exception{
        return uploadService.getVideoDetail(uid, id);

    }


}
