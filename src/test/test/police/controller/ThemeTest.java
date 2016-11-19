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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liyy on 16/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ThemeTest {
    private String baseUrl = "http://localhost:12344";
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testThemeSummary() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/forum/theme/summary"))
                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testThemeDetail() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/forum/theme/detail")
                .param("id", "2"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void testThemeReply() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/forum/theme/reply")
                .header("User-Token", "3542032b1b693463834a0080f9503485")
                .param("uid", "2")
                .param("themeId", "2")
                .param("bizId", "1")
                .param("createTime", System.currentTimeMillis() + "")
                .param("comment", "相当重要"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void testThemeCreate() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/forum/theme/create")
                .header("User-Token", "3542032b1b693463834a0080f9503485")
                .param("uid", "2")
                .param("title", "论不装逼的重要性")
                .param("createTime", new Date().getTime() + ""))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}
