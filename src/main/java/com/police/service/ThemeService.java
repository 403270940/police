package com.police.service;

import com.police.dao.ReplyRepository;
import com.police.dao.ThemeRepository;
import com.police.dao.UserRepository;
import com.police.model.BaseResponse;
import com.police.model.Reply;
import com.police.model.Theme;
import com.police.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liyy on 16/11/9.
 */
@Service
public class ThemeService {
    @Autowired
    ThemeRepository themeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReplyRepository replyRepository;

    /*
    **  themeid
    *   title
    *   creator
    *   createTime
    *   commentCount
     */
    public BaseResponse getThemeSumary() {
        List<Theme> themeList = themeRepository.findAll();
        List<Map<String,String>> resultMap = new ArrayList<Map<String,String>>();
        for(Theme theme : themeList){
            User user = userRepository.findById(theme.getUid());
            String phone = user.getPhone();
            int commentCount = replyRepository.countByThemeid(theme.getId());
            Map<String,String> result = new HashMap<String, String>();
            result.put("themeId",theme.getId() + "");
            result.put("title",theme.getTitle());
            result.put("creator",phone);
            result.put("createTime",theme.getCreateTime().getTime() + "");
            result.put("commnetCount",commentCount + "");
            resultMap.add(result);
        }
        return new BaseResponse(0,"",resultMap);
    }

    /*
    **  themeId
    *   title
    *   creator
    *   createTime
    *   commentCount
    *   comments:
    *       id
    *       bizId
    *       customer
    *       createTime
    *       comment
     */
    public BaseResponse getThemeDetail(String id) {
        Theme theme = themeRepository.findById(Integer.valueOf(id));
        List<Reply> replyList = replyRepository.findByThemeid(theme.getId());
        Map<String,Object> result = new HashMap<String, Object>();
        Map<String,Object> themeMap = new HashMap<String, Object>();
        themeMap.put("themeId",theme.getId());
        themeMap.put("title",theme.getTitle());
        themeMap.put("creator",theme.getCreator());
        themeMap.put("createTime",theme.getCreateTime().getTime() + "");
        themeMap.put("commnetCount",replyList.size());
        List<Map<String,String>> replies = new ArrayList<Map<String, String>>();
        for(Reply reply : replyList){
            Map<String,String> replyResult = new HashMap<String, String>();
            replyResult.put("id",reply.getId() + "");
            replyResult.put("bizId",reply.getBizid() + "");
            replyResult.put("customer",reply.getCustomer());
            replyResult.put("createTime",reply.getCreateTime().getTime() + "");
            replyResult.put("comment",reply.getComment());
            replies.add(replyResult);
        }
        result.put("theme",themeMap);
        result.put("comments",replies);
        return new BaseResponse(0,"",result);
    }

    public BaseResponse createThemeReply(int uid, int themeId, int bizId, String createTime, String comment) {
        Reply reply = new Reply();
        reply.setUid(uid);
        User user = userRepository.findById(uid);
        String phone = user.getPhone();
        String tmpPhone = phone.substring(0,3) + "xxxx" + phone.substring(7,11);
        reply.setCustomer(tmpPhone);
        reply.setThemeid(themeId);
        reply.setBizid(bizId);
        reply.setCreateTime(new Date(Long.valueOf(createTime)));
        reply.setComment(comment);
        replyRepository.save(reply);
        return new BaseResponse(0,"");
    }

    public BaseResponse createTheme(int uid, String title, String createTime) {
        Theme theme = new Theme();
        theme.setUid(Integer.valueOf(uid));
        User user = userRepository.findById(uid);
        String phone = user.getPhone();
        String tmpPhone = phone.substring(0,3) + "xxxx" + phone.substring(7,11);
        theme.setCreator(tmpPhone);
        theme.setTitle(title);
        theme.setCreateTime(new Date(Long.valueOf(createTime)));
        themeRepository.save(theme);
        return new BaseResponse(0,"");
    }
}
