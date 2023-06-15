package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ComenteSobreIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPaginaInicial() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/comente-sobre"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<form")));
    }

    @Test
    public void testPaginaComentarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/comente-sobre/metodos-ageis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("comentarios"))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<form")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<h2>Comentários anteriores:</h2>")));
    }

}
