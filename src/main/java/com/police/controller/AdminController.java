package com.police.controller;

import com.police.model.BaseResponse;
import com.police.model.UploadModel;
import com.police.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liyy on 16/12/10.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "index")
    public String upload(){
        return "index";
    }

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "theme")
    public String theme(){
        return "theme";
    }

    @RequestMapping(value = "notice")
    public String notice(){
        return "notice";
    }

    @RequestMapping(value = "welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "api/userlist")
    @ResponseBody
    public BaseResponse userList(@RequestParam(value = "pageNo",defaultValue = "") int pageNo) throws Exception{
        return adminService.userList();
    }

    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse adminLogin(@RequestParam(value="phone",defaultValue = "")String phone,
                                   @RequestParam(value="password",defaultValue = "")String password,
                                   HttpServletResponse response){
        BaseResponse baseResponse = adminService.adminLogin(phone, password);
        if(baseResponse.getCode() == 0){
            Map<String,String> data = (Map<String,String>)baseResponse.getData();
            String token = data.get("token");
            Cookie cookie = new Cookie("User-Token",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setHeader("Location","/admin/index");
            response.setStatus(302);
        }
        return baseResponse;
    }


    @RequestMapping(value = "api/logout",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse logout(HttpServletRequest request ,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String token = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("User-Token")){
                token = cookie.getValue();
            }
        }
        BaseResponse baseResponse = adminService.logout(token);
        if(baseResponse.getCode() == 0){
            response.setHeader("Location","/admin/login");
            response.setStatus(302);
        }
        return baseResponse;
    }

    @RequestMapping(value = "api/modifyuser",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse modifyUser(@RequestParam(value="id",defaultValue = "")int id,
                                   @RequestParam(value="phone",defaultValue = "")String phone,
                                   @RequestParam(value="password",defaultValue = "")String password){
        BaseResponse baseResponse = adminService.modifyUser(id, phone, password);
        return baseResponse;
    }
    @RequestMapping(value = "api/deleteuser",method = RequestMethod.POST)
      @ResponseBody
      public BaseResponse deleteUser(@RequestParam(value="id",defaultValue = "")int id){
        BaseResponse baseResponse = adminService.deleteUser(id);
        return baseResponse;
    }

    @RequestMapping(value = "api/adduser",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addUser( @RequestParam(value="phone",defaultValue = "")String phone,
                                 @RequestParam(value="password",defaultValue = "")String password){
        BaseResponse baseResponse = adminService.addUser(phone, password);
        return baseResponse;
    }

    @RequestMapping(value = "image")
    public String image(){
        return "image";
    }

    @RequestMapping(value = "api/imagelist")
    @ResponseBody
    public BaseResponse imageList(@RequestParam(value="pageNo",defaultValue = "1") int pageNo){
        return adminService.getImageList(pageNo);
    }

    @RequestMapping(value = "api/image/delete")
    @ResponseBody
    public BaseResponse imageDelete(@RequestParam(value="id",defaultValue = "0") int id){
        return adminService.deleteUpload(id);
    }

    @RequestMapping(value = "video")
    public String video(){
        return "video";
    }

    @RequestMapping(value = "api/videolist")
    @ResponseBody
    public BaseResponse videoList(@RequestParam(value="pageNo",defaultValue = "1") int pageNo){
        return adminService.getVideoList(pageNo);
    }

    @RequestMapping(value = "api/upload/delete")
    @ResponseBody
    public BaseResponse videoDelete(@RequestParam(value="id",defaultValue = "0") int id){
        return adminService.deleteUpload(id);
    }
    @RequestMapping(value="api/upload/download")
    @ResponseBody
    public ResponseEntity<byte[]> download(@RequestParam(value = "id",defaultValue = "") int upload_id) throws Exception{
        return adminService.download(upload_id);
    }

    @RequestMapping(value = "user")
    public String user(){
        return "user";
    }



}
