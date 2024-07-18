package desafio.votacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.exception.NotFoundException;
import desafio.votacao.model.Usuario;
import desafio.votacao.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl {
    
    @Autowired
    UsuarioRepository repository;

    public Optional<Usuario> buscarUsuario(String cpf){
        Optional<Usuario> usuario = repository.findByCpf(cpf);

        if (usuario.isEmpty()) {
           throw new NotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}