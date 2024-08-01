package desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;
import desafio.votacao.service.Voto.VotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/voto")
@CrossOrigin(origins = "http://localhost:3000")
public class VotoController {
    
    @Autowired
    VotoService service;

    @PostMapping("{id}")
    public ResponseEntity<ResponseVotoDto> registrarVoto(@PathVariable Long id, @RequestBody @Valid RequestVotoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarVoto(id, dto));
    }

}
