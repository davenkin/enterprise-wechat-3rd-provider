package davenkin.enterprise.wechat.about;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by yteng on 4/3/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AboutApiTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSayAnotherHelloWorld() {
        String result = restTemplate.getForObject("/about", String.class);
        assertNotNull(result);
    }

}
