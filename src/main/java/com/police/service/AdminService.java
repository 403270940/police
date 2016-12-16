package com.police.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.police.dao.*;
import com.police.model.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyy on 16/12/10.
 */
@Service
public class AdminService {
    @Autowired
    UploadRepository uploadRepository;
    @Autowired
    UserRepository customerRepository;
    @Autowired
    AdminUserRepository adminUserRepository;
    @Autowired
    AdminTokenRepository adminTokenRepository;
    @Autowired
    LogEventService logEventService;

    public BaseResponse adminLogin(String phone,String password){
        BaseResponse baseResponse;
        AdminUser user = adminUserRepository.findByPhone(phone);
        if(user != null && user.getPassword().equals(password)){
            String token = getMD5(phone + password + System.currentTimeMillis());
            Map<String,Object> responseBody = new HashMap<String, Object>();
            responseBody.put("token",token);
            responseBody.put("uid",user.getId());
            String tmpPhone = phone.substring(0,3) + "xxxx" + phone.substring(7,11);
            AdminToken adminToken = new AdminToken(user.getId(),token);
            adminTokenRepository.save(adminToken);
            responseBody.put("phone", tmpPhone);
            logEventService.recieve(new LogEvent(phone,"登录成功"));
            baseResponse = new BaseResponse(0,"登录成功!",responseBody);
        }else{
            baseResponse = new BaseResponse(-1,"账号密码不匹配!");
        }
        return baseResponse;
    }

    public BaseResponse getImageList(int pageNo){
        List<UploadModel> images = uploadRepository.findByType("image");
        List<Map<String,Object>> imagesList = Lists.newArrayList();
        for(UploadModel image : images){
            String uid = image.getUid();
            String phone = customerRepository.findById(Integer.valueOf(uid)).getPhone();
            Map<String,Object> imageMap = Maps.newHashMap();
            imageMap.put("id", image.getId());
            imageMap.put("phone", phone);
            imageMap.put("comment", image.getComment());
            imageMap.put("createtime", image.getCreateTime().getTime());
            imageMap.put("location", image.getLocation());
            imageMap.put("url", "/upload/image/app.png");
            imagesList.add(imageMap);
        }
        BaseResponse baseResponse = new BaseResponse(0,"success",imagesList);
        return baseResponse;
    }

    public BaseResponse getVideoList(int pageNo){
        List<UploadModel> videos = uploadRepository.findByType("video");
        List<Map<String,Object>> videosList = Lists.newArrayList();
        for(UploadModel video : videos){
            String uid = video.getUid();
            String phone = customerRepository.findById(Integer.valueOf(uid)).getPhone();
            Map<String,Object> videoMap = Maps.newHashMap();
            videoMap.put("id",video.getId());
            videoMap.put("phone",phone);
            videoMap.put("comment",video.getComment());
            videoMap.put("createtime",video.getCreateTime().getTime());
            videoMap.put("location",video.getLocation());
            videoMap.put("url","/upload/image/app.png");
            videosList.add(videoMap);
        }
        BaseResponse baseResponse = new BaseResponse(0,"success",videosList);
        return baseResponse;
    }

    public ResponseEntity<byte[]> download(int upload_id) throws Exception{
        UploadModel uploadModel =uploadRepository.findById(upload_id);
        String filePath = uploadModel.getFilepath();
        File file = new File(filePath);
        String dfileName = file.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    public BaseResponse deleteUpload(int id) {
        UploadModel uploadModel = uploadRepository.findById(id);
        File file = new File(uploadModel.getFilepath());
        file.delete();
        uploadRepository.delete(uploadModel);
        return new BaseResponse(0,"Success");
    }

    public BaseResponse userList() {
        List<User> users = (List<User>) customerRepository.findAll();
        return new BaseResponse(0,"",users);
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
//            throw new SpeedException("MD5加密出现错误");
            return "";
        }
    }

    public BaseResponse modifyUser(int id,String phone, String password) {
        User user = customerRepository.findById(id);
        user.setPhone(phone);
        user.setPassword(password);
        customerRepository.save(user);
        return new BaseResponse(0,"修改成功");
    }

    public BaseResponse deleteUser(int id) {
        customerRepository.delete(id);
        return new BaseResponse(0,"删除成功");
    }

    public BaseResponse addUser(String phone, String password) {
        User user =  new User(phone,password);
        customerRepository.save(user);
        return new BaseResponse(0,"添加成功");
    }

    public BaseResponse logout(String token) {
        AdminToken adminToken = adminTokenRepository.findByToken(token);
        adminTokenRepository.delete(adminToken);
        return new BaseResponse(0,"退出成功!");
    }
}
