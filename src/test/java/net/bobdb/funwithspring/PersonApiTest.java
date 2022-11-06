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
    public static final String TEST_NAME_NEW = "Rhaenerys";
    public static final String TEST_NAME_EXISTS = "Daemon";

    @LocalServerPort
    int TEST_PORT;

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
        assertTrue(names.containsAll(List.of("Viserys", "Corlys")));

    }

    @Test
    void findById() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + TEST_PORT + "/api/person/" + 3;
        URI uri = new URI(baseUrl);

        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("Corlys", result.getBody().getName());

    }

    @Test
    void findByName() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String url = "http://localhost:" + TEST_PORT + "/api/search/Corlys";

        ResponseEntity<Person> result = restTemplate.getForEntity(url, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("Corlys", result.getBody().getName());

    }

    @Test
    void create() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + TEST_PORT + "/api/person/" + TEST_NAME_NEW;
        Person person = new Person(TEST_NAME_NEW);
        ResponseEntity<Person> result = restTemplate.postForEntity(baseUrl, person, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(result.getBody().getName(), person.getName());

    }

    @Test
    void update() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + TEST_PORT + "/api/person/" + TEST_NAME_NEW;
        Person person = new Person(TEST_NAME_NEW);
        ResponseEntity<Person> result = restTemplate.postForEntity(baseUrl, person, Person.class);

        Long id = result.getBody().getId();

        final String url = "http://localhost:" + TEST_PORT + "/api/person/" + id;

        result = restTemplate.getForEntity(url, Person.class);
        result.getBody().setName(TEST_NAME_NEW);
        restTemplate.put(url, result.getBody());

        result = restTemplate.getForEntity(url, Person.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(result.getBody().getName(), person.getName());

    }

    @Test
    void delete() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final String url = "http://localhost:" + TEST_PORT + "/api/person/1";
        ResponseEntity<Person> result = restTemplate.getForEntity(url, Person.class);
        restTemplate.delete(url);

        //TODO how to confirm deletion


    }

}