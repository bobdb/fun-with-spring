package net.bobdb.funwithspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonApiTest {

    @LocalServerPort
    int port;

    @Test
    void person() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:"+port+"/api/person/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Daemon", result.getBody().getName());

    }

}