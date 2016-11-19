package test.police;

import com.police.Application;
import com.police.model.LogEvent;
import com.police.service.LogEventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liyy on 16/11/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {
    @Autowired
    private LogEventService logEventService;

    @Test
    public void testRecieve(){
        LogEvent logEvent = new LogEvent("13312312312","login");
        logEventService.recieve(logEvent);
    }
}
