package desafio.votacao.service.Usuario;

import java.util.List;
import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.model.Usuario;

public interface UsuarioService {
    
    ResponseUsuarioDto registrar(RequestUsuarioDto dto);
    Usuario buscarUsuarioPorCpf(String cpf);
    List<ResponseUsuarioDto> visualizar();
}
