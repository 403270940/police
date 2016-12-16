package test.police.controller;

import com.police.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liyy on 16/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UploadTest {
    private String baseUrl = "http://localhost:12344";
    private String token = "2745b6a2e41857e00ffecfb7fa79d554";
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testImage() throws Exception{
//        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(baseUrl + "/api/checkphone").param("phone","13818154818");
        String result = mockMvc.perform(fileUpload(baseUrl + "/api/image").file(new MockMultipartFile("image","blog.jpg","text/plain",new FileInputStream("blog.jpg")))
                .header("User-Token",token)
                .param("uid", "1")
                .param("location", "上海市浦东区")
                .param("createTime", new Date().getTime() + "")
                .param("comment", "有人装逼照片"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void testImageSummary() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/image/summary")
                .header("User-Token", token)
                .param("uid", "1"))
                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testImageDetail() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/image/detail")
                .header("User-Token", token)
                .param("uid", "1")
                .param("id", "1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }



    @Test
    public void testVideo() throws Exception{
//        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(baseUrl + "/api/checkphone").param("phone","13818154818");
        String result = mockMvc.perform(fileUpload(baseUrl + "/api/video").file(new MockMultipartFile("video","blog.jpg","text/plain",new FileInputStream("blog.jpg")))
                .header("User-Token", token)
                .param("uid", "1")
                .param("location", "上海市浦东区")
                .param("createTime", new Date().getTime() + "")
                .param("comment", "有人装逼照片"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void testVideoSummary() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/video/summary")
                .header("User-Token", token)
                .param("uid", "1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testVideoDetail() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/video/detail")
                .header("User-Token", token)
                .param("uid", "1")
                .param("id", "2"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
