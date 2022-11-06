package net.bobdb.funwithspring;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String url = "http://localhost:" + port + "/greeting";
        assertThat(this.restTemplate.getForObject(url, String.class))
                                    .contains("Hello, World");
    }

    @Test
    public void namedGreetingShouldReturnMessageWithName() throws Exception {
        String name = "Somebody";
        String url = "http://localhost:" + port + "/greeting?name=" + name;
        assertThat(this.restTemplate.getForObject(url, String.class))
                .contains("Hello, " + name);
    }

}