package desafio.votacao.Pauta;

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
import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.fixture.PautaDtoFixture;
import desafio.votacao.fixture.SqlProvider;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class  ControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper;
    private RequestPautaDto dto;
    private String json;

    @Test
    @DisplayName("Deve permitir cadastrar uma pauta")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void cadastrarTest() throws Exception {

        mapper = new ObjectMapper();
        dto = PautaDtoFixture.PautaDtoValido();
        json = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/pauta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve permitir visualizar todas as pautas")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertPauta),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void visualizarPautasTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/visualizar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Teste 1"));
    }

    @Test
    @DisplayName("Deve permitir visualizar uma pauta selecionada")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertPauta),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void visualizarPautaSelecionadaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pauta/visualizar/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.titulo").value("Teste 2"));
    }

}
