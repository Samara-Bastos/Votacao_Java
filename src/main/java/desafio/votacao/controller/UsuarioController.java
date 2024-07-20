package desafio.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.votacao.dto.RequestUsuarioDto;
import desafio.votacao.dto.ResponseUsuarioDto;
import desafio.votacao.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioServiceImpl service;

    @PostMapping()
    public void criarUsuario(@RequestBody RequestUsuarioDto dto) {
        service.create(dto);
    }

    @GetMapping()
    public ResponseEntity<List<ResponseUsuarioDto>> visualizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizar());
    }

}
