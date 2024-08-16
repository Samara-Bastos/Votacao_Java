package desafio.votacao.Voto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.fixture.SqlProvider;
import desafio.votacao.fixture.VotoDtoFixture;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String json;

    private RequestVotoDto dto;

    @Test
    @DisplayName("Deve permitir registrar um voto")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertUsuarioAndSessao),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void registrarVotoTest() throws Exception {
        dto = VotoDtoFixture.votoDtoValido();
        json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/voto/{id}", 1) 
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Não deve permitir registrar voto em sessão de votação fechada")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertUsuarioAndSessaoFechada),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void registrarVotoSessaoFechadaTest() throws Exception {
        RequestVotoDto dto = VotoDtoFixture.votoDtoValido();
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/voto/{id}", 1) 
                .contentType(MediaType.APPLICATION_JSON) 
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Não foi possivel votar, pois essa votação não está ativa"));
    }

    @Test
    @DisplayName("Não deve permitir registrar voto duplicado pelo mesmo usuário")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertUsuarioAndSessaoAndVoto),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void registrarVotoDuplicadoTest() throws Exception {
        dto = VotoDtoFixture.votoDtoValido();
        json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/voto/{id}", 1) 
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict())
                .andExpect(content().string("Esse usuário ja votou nessa pauta"));
    }
}
