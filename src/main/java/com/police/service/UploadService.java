package com.police.service;

import com.police.dao.UploadRepository;
import com.police.model.BaseResponse;
import com.police.model.LogEvent;
import com.police.model.UploadModel;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * Created by liyy on 16/11/9.
 */
@Service
public class UploadService {
    @Autowired
    UploadRepository uploadRepository;
    @Autowired
    LogEventService logEventService;

    public BaseResponse uploadImage(String uid,String location,String createTime,MultipartFile image,String comment) throws Exception{
        String orginalFilename = image.getOriginalFilename();
        String splits[] = orginalFilename.split("\\.");
        String filepath = "/home/li/police/image/" + uid + "_" + DateFormatUtils.format(new Date(Long.valueOf(createTime)), "yyyy-MM-dd_HH:mm:ss") + "." + splits[splits.length - 1];
        image.transferTo(new File(filepath));
        UploadModel uploadModel = new UploadModel(uid,location,new Date(Long.valueOf(createTime)),filepath,comment,"image");
        uploadRepository.save(uploadModel);
        logEventService.recieve(new LogEvent(uid, "上传图片成功"));
        return new BaseResponse(0,"",null);
    }

    public BaseResponse uploadVideo(String uid,String location,String createTime,MultipartFile video,String comment) throws Exception{
        String orginalFilename = video.getOriginalFilename();
        String splits[] = orginalFilename.split("\\.");
        String filepath = "/home/li/police/video/" + uid + "_" + createTime + "." + splits[splits.length - 1];
        video.transferTo(new File(filepath));
        UploadModel uploadModel = new UploadModel(uid,location,new Date(Long.valueOf(createTime)),filepath,comment,"video");
        uploadRepository.save(uploadModel);
        logEventService.recieve(new LogEvent(uid, "上传视频成功"));
        return new BaseResponse(0,"");
    }

    public BaseResponse getImageSummary(String uid) {
        List<UploadModel> uploadModelList = uploadRepository.findByUidAndType(uid, "image");
        List<Map<String,String>> resultList = new ArrayList<Map<String, String>>();
        for(UploadModel uploadModel : uploadModelList){
            Map<String,String> result = new HashMap<String, String>();
            result.put("id",String.valueOf(uploadModel.getId()));
            result.put("comment",uploadModel.getComment());
            result.put("createTime",String.valueOf(uploadModel.getCreateTime().getTime()));
            result.put("location",uploadModel.getLocation());
            resultList.add(result);
        }
        return new BaseResponse(0,"",resultList);
    }

    public BaseResponse getImageDetail(String uid, String id) throws Exception{

        UploadModel uploadModel= uploadRepository.findByUidAndIdAndType(uid, Integer.valueOf(id), "image");
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("id",uploadModel.getId());
        result.put("comment",uploadModel.getComment());
        result.put("createTime",uploadModel.getCreateTime());
        result.put("image", org.aspectj.util.FileUtil.readAsByteArray(new File(uploadModel.getFilepath())));
        result.put("location",uploadModel.getLocation());
        return new BaseResponse(0,"",result);
    }

    public BaseResponse getVideoSummary(String uid) {
        List<UploadModel> uploadModelList = uploadRepository.findByUidAndType(uid,"video");
        List<Map<String,String>> resultList = new ArrayList<Map<String, String>>();
        for(UploadModel uploadModel : uploadModelList){
            Map<String,String> result = new HashMap<String, String>();
            result.put("id",String.valueOf(uploadModel.getId()));
            result.put("comment",uploadModel.getComment());
            result.put("createTime",String.valueOf(uploadModel.getCreateTime().getTime()));
            result.put("location",uploadModel.getLocation());
            resultList.add(result);
        }
        return new BaseResponse(0,"",resultList);
    }

    public BaseResponse getVideoDetail(String uid, String id) throws Exception{
        UploadModel uploadModel= uploadRepository.findByUidAndIdAndType(uid, Integer.valueOf(id), "video");
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("id",uploadModel.getId());
        result.put("video",uploadModel.getComment());
        result.put("createTime",uploadModel.getCreateTime());
        result.put("image", org.aspectj.util.FileUtil.readAsByteArray(new File(uploadModel.getFilepath())));
        result.put("location",uploadModel.getLocation());
        return new BaseResponse(0,"",result);
    }
}
