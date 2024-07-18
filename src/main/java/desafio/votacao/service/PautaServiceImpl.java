package desafio.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.dto.RequestPautaDto;
import desafio.votacao.mapper.PautaMapper;
import desafio.votacao.model.Pauta;
import desafio.votacao.repository.PautaRepository;

@Service
public class PautaServiceImpl {
    
    @Autowired
    PautaRepository repository;

    @Autowired
    SessaoVotacaoServiceImpl sessaoVotacaoService;

    public void create(RequestPautaDto dto){

        Pauta pauta = PautaMapper.INSTANCE.dtoToPauta(dto);
        repository.save(pauta);

        if (dto.ativaSessao() == true) {
            sessaoVotacaoService.abrirSessaoVotacao(pauta);
        }
        
    }
} 