package desafio.votacao.service.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.exception.FindException;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.mapper.UsuarioMapper;
import desafio.votacao.model.Usuario;
import desafio.votacao.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    UsuarioRepository repository;

    @Override
    public ResponseUsuarioDto registrar(RequestUsuarioDto dto){
        verificaSeJaExisteUsuarioComEsseCpf(dto.cpf());
        Usuario usuario = UsuarioMapper.INSTANCE.dtoToUsuario(dto);
        repository.save(usuario);
        return UsuarioMapper.INSTANCE.usuarioToDto(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorCpf(String cpf){
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<ResponseUsuarioDto> visualizar(){
        return repository.findAll().stream().map(ResponseUsuarioDto::new).toList();
    };

    private void verificaSeJaExisteUsuarioComEsseCpf(String cpf){
        Optional<Usuario> usuario = repository.findByCpf(cpf);
        if (usuario.isPresent()) {
           throw new FindException("Já existe um usuário cadastrado com esse CPF");
        }
    }
}