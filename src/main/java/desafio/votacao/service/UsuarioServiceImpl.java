package desafio.votacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.dto.RequestUsuarioDto;
import desafio.votacao.exception.FinfException;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.mapper.UsuarioMapper;
import desafio.votacao.model.Usuario;
import desafio.votacao.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl {
    
    @Autowired
    UsuarioRepository repository;


    public void create(RequestUsuarioDto dto){
        Optional<Usuario> usuarioReturn = repository.findByCpf(dto.cpf());

        if (usuarioReturn.isPresent()) {
           throw new FinfException("Já existe um usuário cadastrado com esse CPF");
        }

        Usuario usuario = UsuarioMapper.INSTANCE.dtoToUsuario(dto);

        repository.save(usuario);
    }

    public Optional<Usuario> buscarUsuario(String cpf){
        Optional<Usuario> usuario = repository.findByCpf(cpf);

        if (usuario.isEmpty()) {
           throw new NotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}