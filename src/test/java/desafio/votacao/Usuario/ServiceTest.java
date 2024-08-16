package desafio.votacao.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.fixture.UsuarioDtoFixture;
import desafio.votacao.model.Usuario;
import desafio.votacao.repository.UsuarioRepository;
import desafio.votacao.service.Usuario.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    
    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioServiceImpl service;

    RequestUsuarioDto requestUsuarioDtoValido;

    ResponseUsuarioDto response;

    @BeforeEach
    public void setup() {
        requestUsuarioDtoValido = UsuarioDtoFixture.usuarioDtoValido();
    }


    @Test
    @DisplayName("Deve cadastrar um usuario")
    void createTest(){
        when(repository.findByCpf(anyString())).thenReturn(Optional.empty());
        response = service.registrar(requestUsuarioDtoValido);

        assertNotNull(response);
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve encontrar um usuario")
    void buscarUsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setCpf(requestUsuarioDtoValido.cpf());

        when(repository.findByCpf(anyString())).thenReturn(Optional.of(usuario));

        Usuario usuarioReturn = service.buscarUsuarioPorCpf(requestUsuarioDtoValido.cpf());

        assertNotNull(usuarioReturn);
        assertEquals(usuario.getCpf(), usuarioReturn.getCpf());
    }

    @Test
    @DisplayName("Não deve encontrar um usuario")
    void buscarUsuarioMasNaoEncontraTest(){
         when(repository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarUsuarioPorCpf(requestUsuarioDtoValido.cpf());
        });
    }

    @Test
    @DisplayName("Deve visualizar todos os usuários")
    void visualizarUsuariosTest(){
        List<Usuario> usuarios = new ArrayList<>();
        when(repository.findAll()).thenReturn(usuarios);

        List<ResponseUsuarioDto> responseUsuarioDtos = service.visualizar();
        assertTrue(responseUsuarioDtos.isEmpty());
    }

}
