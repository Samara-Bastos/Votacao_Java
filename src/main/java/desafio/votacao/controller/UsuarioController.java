package desafio.votacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import desafio.votacao.dto.Usuario.RequestUsuarioDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
import desafio.votacao.service.Usuario.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:3000", "https://votacao-mgdy.onrender.com"})
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @PostMapping()
    public ResponseEntity<ResponseUsuarioDto> criarUsuario(@RequestBody @Valid RequestUsuarioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @GetMapping("/visualizar")
    public ResponseEntity<List<ResponseUsuarioDto>> visualizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizar());
    }

}
