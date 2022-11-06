package net.bobdb.funwithspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/person"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Daemon")))
                    .andExpect(content().string(containsString("Viserys")))
                    .andExpect(content().string(containsString("Corlys")))
                    .andReturn();

    }

}