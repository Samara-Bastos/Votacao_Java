package desafio.votacao.Pauta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.dto.Pauta.ResponsePautaDto;
import desafio.votacao.fixture.PautaDtoFixture;
import desafio.votacao.model.Pauta;
import desafio.votacao.repository.PautaRepository;
import desafio.votacao.service.Pauta.PautaServiceImpl;
import desafio.votacao.service.SessaoVotacao.SessaoVotacaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    
    @Mock
    PautaRepository repository;

    @Mock
    SessaoVotacaoServiceImpl serviceSessaoVotacao;

    @InjectMocks 
    PautaServiceImpl service;

    RequestPautaDto requestPautaDtoValido;

    RequestPautaDto requestPautaDtoInvalido;

    ResponsePautaDto responsePautaDto;

    Pauta pauta;


    @BeforeEach
    public void setup() {
        requestPautaDtoValido = PautaDtoFixture.PautaDtoValido();
        requestPautaDtoInvalido = PautaDtoFixture.PautaDtoInvalido();
        pauta = new Pauta();
        pauta.setId(1L);
    }

    @Test
    @DisplayName("Deve cadastrar uma pauta")
    void createTest(){
        when(repository.save(any(Pauta.class))).thenReturn(pauta);

        responsePautaDto = service.registrar(requestPautaDtoValido);

        assertNotNull(responsePautaDto);
        assertEquals(responsePautaDto.titulo(), requestPautaDtoValido.titulo());
        assertEquals(responsePautaDto.categoria(), requestPautaDtoValido.categoria());

    }

    @Test
    @DisplayName("Deve visualizar todas as pautas")
    void visualizarPautaTest(){
        List<Pauta> pautas = new ArrayList<>();
        when(repository.findAll()).thenReturn(pautas);

        List<ResponsePautaDto> responsePautaDto = service.visualizar();
        assertTrue(responsePautaDto.isEmpty());
    }

    @Test
    @DisplayName("Deve visualizar a pauta especificada")
    void visualizarPautaEspecificaTest(){
        when(repository.findById(pauta.getId())).thenReturn(Optional.of(pauta)); 

        responsePautaDto = service.visualizarPautaSelecionada(pauta.getId());

        assertNotNull(responsePautaDto);
    }

}
