package desafio.votacao.SessaoVotacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.fixture.SessaoVotacaoFixture;
import desafio.votacao.fixture.VotoDtoFixture;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.repository.SessaoVotacaoRepository;
import desafio.votacao.service.SessaoVotacao.SessaoVotacaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    
    @Mock
    SessaoVotacaoRepository repository;

    @InjectMocks 
    SessaoVotacaoServiceImpl service;

    SessaoVotacao sessaoVotacao;

    Pauta pauta;

    @BeforeEach
    public void setup() {
        sessaoVotacao = SessaoVotacaoFixture.sessaoVotacaoValida();
    }

    @Test
    @DisplayName("Deve abrir uma sessão de votação")
    void create(){
        pauta = new Pauta();
        pauta.setId(1L);

        service.abrirSessaoVotacao(2, pauta);
        verify(repository, times(1)).save(any(SessaoVotacao.class));
    }


    @Test
    @DisplayName("Deve encontrar uma sessão de votação")
    void buscarSessaoVotacaoTest(){
        when(repository.findById(1L)).thenReturn(Optional.of(sessaoVotacao));

        Optional<SessaoVotacao> sessaoVotacaoReturn = service.buscarSessaoVotacao(1L);

        assertNotNull(sessaoVotacaoReturn);
        assertEquals(sessaoVotacao, sessaoVotacaoReturn.get());
    }

    @Test
    @DisplayName("Não deve encontrar uma sessão de votação")
    void buscarSessaoVotacaoMasNaoEncontraTest(){
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarSessaoVotacao(1L);
        });
    }

    @Test
    @DisplayName("Deve contabilizar o voto na sessão e salvar")
    void contabilizarVotoNaSessaoTest(){
        when(repository.findById(1L)).thenReturn(Optional.of(sessaoVotacao));
        RequestVotoDto voto = VotoDtoFixture.votoDtoValido();

        service.contabilizarVotoNaSessao(1L, voto);
        verify(repository, times(1)).save(sessaoVotacao);
    }
}
