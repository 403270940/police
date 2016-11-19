package test.police.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.police.Application;
import com.police.controller.RegisterController;
import com.police.service.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by liyy on 16/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RegisterTest {
    private String baseUrl = "http://localhost:12344";
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    @Mock
    RegisterService registerService;
    @InjectMocks
    RegisterController registerController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void testCheckPhone() throws Exception{
//        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(baseUrl + "/api/checkphone").param("phone","13818154818");
        String result = mockMvc.perform(post(baseUrl + "/api/checkphone").param("phone", "13818154818"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testGetCaptcha(){
        try {
            String result = mockMvc.perform(post(baseUrl + "/api/getcaptcha").param("phone", "13818154818"))
                    .andReturn().getResponse().getContentAsString();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testRegister() throws Exception{
        String result = mockMvc.perform(post(baseUrl + "/api/register")
                .param("phone", "13818154818")
                .param("password", "123456")
                .param("captcha", "123456")
                .param("captchaId", "1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
