package test.police.service;

import com.police.Application;
import com.police.model.BaseResponse;
import com.police.model.LogEvent;
import com.police.service.LogEventService;
import com.police.service.UploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

/**
 * Created by liyy on 16/11/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UploadServiceTest {
    @Autowired
    private UploadService uploadService;

    @Test
    public void testUploadImage() throws Exception{
        String uid = "1";
        String location = "上海市人民广场";
        String createTime = "" + System.currentTimeMillis();
        MultipartFile image = new MockMultipartFile("blog.png",new FileInputStream("blog.png"));
        String comment = "警察叔叔快来，这里有人装逼！";
        uploadService.uploadImage(uid,location,createTime,image,comment);
    }


    @Test
    public void testGetImageSummary() throws Exception{
        BaseResponse baseResponse = uploadService.getImageSummary(1 + "");
        System.out.println(baseResponse);
    }
    @Test
    public void testGetImageDetail() throws Exception{
        BaseResponse baseResponse = uploadService.getImageDetail("1","4");
        System.out.println(baseResponse);
    }

    @Test
    public void testUploadVideo() throws Exception{
        String uid = "1";
        String location = "上海市外滩";
        String createTime = "" + System.currentTimeMillis();
        MultipartFile image = new MockMultipartFile("blog.png",new FileInputStream("blog.png"));
        String comment = "警察叔叔快来，这里有人抢劫！";
        uploadService.uploadVideo(uid, location, createTime, image, comment);
    }

    @Test
    public void testGetVideoSummary() throws Exception{
        BaseResponse baseResponse = uploadService.getVideoSummary(1 + "");
        System.out.println(baseResponse);
    }

}
