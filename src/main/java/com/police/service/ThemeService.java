//package com.police.service;
//
//import com.police.dao.ReplyRepository;
//import com.police.dao.ThemeRepository;
//import com.police.dao.UserRepository;
//import com.police.model.BaseResponse;
//import com.police.model.Reply;
//import com.police.model.ResponseModel.ThemeSummary;
//import com.police.model.Theme;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by liyy on 16/11/9.
// */
//@Service
//public class ThemeService {
//    @Autowired
//    ThemeRepository themeRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ReplyRepository replyRepository;
//
//    public BaseResponse getThemeDetail() {
//        List<Theme> themeList = themeRepository.findAll();
//        for(Theme theme : themeList){
//            ThemeSummary themeSummary = new ThemeSummary();
//            themeSummary.setId(theme.getId());
//            themeSummary.setTitle(theme.getTitle());
////            String phone = userRepository.findById(theme.getUid());
////            themeSummary.se();
//        }
//        return new BaseResponse(0,"",themeList);
//    }
//
//    public BaseResponse getThemeDetail(String id) {
//        Theme theme = themeRepository.findById(Integer.valueOf(id));
//        return null;
//    }
//
//    public BaseResponse createThemeReply(String uid, String themeId, String bizId, String createTime, String comment) {
//        Reply reply = new Reply();
//        reply.setUid(Integer.valueOf(uid));
//        reply.setThemeid(Integer.valueOf(themeId));
//        reply.setBizid(Integer.valueOf(bizId));
//        reply.setCreateTime(new Date(Long.valueOf(createTime)));
//        reply.setComment(comment);
//        replyRepository.save(reply);
//        return new BaseResponse(0,"");
//    }
//
//    public BaseResponse createTheme(String uid, String title, String createTime) {
//        Theme theme = new Theme();
//        theme.setUid(Integer.valueOf(uid));
//        theme.setTitle(title);
//        theme.setCreateTime(new Date(Long.valueOf(createTime)));
//        themeRepository.save(theme);
//        return new BaseResponse(0,"");
//    }
//}
