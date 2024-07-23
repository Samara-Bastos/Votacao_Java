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
public class UsuarioServiceImpl {
    
    @Autowired
    UsuarioRepository repository;


    public ResponseUsuarioDto create(RequestUsuarioDto dto){
        Optional<Usuario> usuarioReturn = repository.findByCpf(dto.cpf());

        if (usuarioReturn.isPresent()) {
           throw new FindException("Já existe um usuário cadastrado com esse CPF");
        }

        Usuario usuario = UsuarioMapper.INSTANCE.dtoToUsuario(dto);

        repository.save(usuario);

        return UsuarioMapper.INSTANCE.usuarioToDto(usuario);
    }

    public Optional<Usuario> buscarUsuario(String cpf){
        Optional<Usuario> usuario = repository.findByCpf(cpf);

        if (usuario.isEmpty()) {
           throw new NotFoundException("Usuário não encontrado");
        }

        return usuario;
    }

    public List<ResponseUsuarioDto> visualizar(){
        return repository.findAll().stream().map(ResponseUsuarioDto::new).toList();
    };
}