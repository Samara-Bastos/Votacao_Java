package desafio.votacao.service.Usuario;

import java.util.List;
import java.util.Optional;

import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.model.Usuario;

public interface UsuarioService {
    
    ResponseUsuarioDto create(RequestUsuarioDto dto);
    Optional<Usuario> buscarUsuario(String cpf);
    List<ResponseUsuarioDto> visualizar();
}
