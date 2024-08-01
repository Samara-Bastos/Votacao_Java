package desafio.votacao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.dto.Pauta.ResponsePautaDto;
import desafio.votacao.service.Pauta.PautaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pauta")
@CrossOrigin(origins = "http://localhost:3000")
public class PautaController {
    
    @Autowired
    PautaService service;

    @PostMapping()
    public ResponseEntity<ResponsePautaDto> criarPauta(@RequestBody @Valid RequestPautaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    
    @GetMapping("/view")
    public ResponseEntity<List<ResponsePautaDto>> visualizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizar());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ResponsePautaDto> visualizarPautaSelecionada(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizarPautaSelecionada(id));
    }


}
