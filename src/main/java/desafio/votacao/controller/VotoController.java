package desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import desafio.votacao.dto.RequestVotoDto;
import desafio.votacao.dto.ResponseVotoDto;
import desafio.votacao.service.VotoServiceImpl;

@RestController
@RequestMapping("/voto")
public class VotoController {
    
    @Autowired
    VotoServiceImpl service;

    @PostMapping("{id}")
    public void registrarVoto(@PathVariable Long id, @RequestBody RequestVotoDto dto){
        service.registrarVoto(id, dto);
    }

    @GetMapping()
    public ResponseEntity<List<ResponseVotoDto>> visualizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizar());
    }

}
