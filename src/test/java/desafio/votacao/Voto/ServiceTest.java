package desafio.votacao.Voto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;
import desafio.votacao.exception.VotacaoFechadaException;
import desafio.votacao.fixture.SessaoVotacaoFixture;
import desafio.votacao.fixture.UsuarioDtoFixture;
import desafio.votacao.fixture.VotoDtoFixture;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Usuario;
import desafio.votacao.model.Voto;
import desafio.votacao.repository.VotoRepository;
import desafio.votacao.service.SessaoVotacao.SessaoVotacaoServiceImpl;
import desafio.votacao.service.Usuario.UsuarioServiceImpl;
import desafio.votacao.service.Voto.VotoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    
    @Mock
    VotoRepository repository;

    @Mock
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @Mock
    private UsuarioServiceImpl usuarioService;

    @InjectMocks
    VotoServiceImpl service;

    RequestVotoDto requestVotoDtoValido;
    ResponseVotoDto response;
    SessaoVotacao sessaoVotacao;
    Usuario usuario;

    @BeforeEach
    public void setup() {
        requestVotoDtoValido = VotoDtoFixture.votoDtoValido();

        usuario = new Usuario();
        usuario.setCpf(UsuarioDtoFixture.usuarioDtoValido().cpf());

        sessaoVotacao = SessaoVotacaoFixture.sessaoVotacaoValida();
    }

    @Test
    @DisplayName("Deve registrar um voto")
    void registrarVoto(){
        when(sessaoVotacaoService.buscarSessaoVotacao(anyLong())).thenReturn(sessaoVotacao);
        when(usuarioService.buscarUsuarioPorCpf(anyString())).thenReturn(usuario);
        when(repository.findByUsuario(any(Usuario.class))).thenReturn(new ArrayList<>());

        response = service.registrarVoto(1L, requestVotoDtoValido);

        assertNotNull(response);
        verify(repository, times(1)).save(any(Voto.class));
    }


    @Test
    @DisplayName("Não deve registrar um voto")
    void naoRegistrarVoto(){
        sessaoVotacao.setAtiva(false);

        when(sessaoVotacaoService.buscarSessaoVotacao(anyLong())).thenReturn(sessaoVotacao);

        assertThrows(VotacaoFechadaException.class, () -> {
            service.registrarVoto(1L, requestVotoDtoValido);
        });
    }

}
