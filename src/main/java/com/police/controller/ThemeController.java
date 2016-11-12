//package com.police.controller;
//
//import com.police.model.BaseResponse;
//import com.police.service.ThemeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
///**
// * Created by liyy on 16/10/24.
// */
//@Controller
//@RequestMapping("/api")
//public class ThemeController {
//    @Autowired
//    ThemeService themeService;
//
//    @RequestMapping(value = "api/forum/theme/summary")
//    @ResponseBody
//    public BaseResponse getThemeSummary(){
//        return themeService.getThemeDetail();
//    }
//
//    @RequestMapping(value = "api/forum/theme/detail")
//    @ResponseBody
//    public BaseResponse getThemeSummary(@RequestParam(value = "id",defaultValue = "") String id){
//        return themeService.getThemeDetail(id);
//
//    }
//
//
//
//    @RequestMapping(value = "api/forum/theme/reply",method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse getThemeReply(@RequestParam(value = "uid",defaultValue = "") String uid,
//                                      @RequestParam(value = "themeId",defaultValue = "") String themeId,
//                                      @RequestParam(value = "bizId",defaultValue = "") String bizId,
//                                      @RequestParam(value = "createTime",defaultValue = "") String createTime,
//                                      @RequestParam(value = "comment",defaultValue = "") String comment){
//        return themeService.createThemeReply(uid, themeId, bizId, createTime, comment);
//
//    }
//
//    @RequestMapping(value = "api/forum/theme/create",method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse createTheme(@RequestParam(value = "uid",defaultValue = "") String uid,
//                                      @RequestParam(value = "title",defaultValue = "") String themeId,
//                                      @RequestParam(value = "createTime",defaultValue = "") String createTime){
//        return themeService.createTheme(uid, themeId, createTime);
//
//    }
//
//}
