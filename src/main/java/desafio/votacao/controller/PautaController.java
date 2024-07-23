package desafio.votacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import desafio.votacao.dto.RequestPautaDto;
import desafio.votacao.dto.ResponsePautaDto;
import desafio.votacao.service.PautaServiceImpl;

@RestController
@RequestMapping("/pauta")
public class PautaController {
    
    @Autowired
    PautaServiceImpl service;

    @PostMapping()
    public void criarPauta(@RequestBody RequestPautaDto dto) {
        service.create(dto);
    }
    
    @GetMapping()
    public ResponseEntity<List<ResponsePautaDto>> visualizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizar());
    }


}
