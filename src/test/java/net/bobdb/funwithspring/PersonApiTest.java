package net.bobdb.funwithspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonApiTest {

    public static final long TEST_ID = 1L;

    @LocalServerPort
    int TEST_PORT;

    @Test
    void findById() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + TEST_PORT + "/api/person/" + TEST_ID;
        URI uri = new URI(baseUrl);

        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("Daemon", result.getBody().getName());

    }

    @Test
    void findAll() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + TEST_PORT + "/api/person/";
        URI uri = new URI(baseUrl);
        ResponseEntity<Person[]> result = restTemplate.getForEntity(uri,  Person[].class);
        assertNotNull(result.getBody());
        List<Person> persons = Arrays.asList(result.getBody());

        assertEquals(200, result.getStatusCodeValue());

        List<String> names = persons.stream().map(Person::getName).toList();
        assertTrue(names.containsAll(List.of("Daemon", "Viserys", "Corlys")));

    }

}