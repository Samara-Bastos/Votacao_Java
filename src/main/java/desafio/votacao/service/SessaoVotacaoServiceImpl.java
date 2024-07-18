package desafio.votacao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.enums.Situacao;
import desafio.votacao.exception.NotFoundException;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl {
    
    @Autowired
    SessaoVotacaoRepository repository;

    public void abrirSessaoVotacao(Pauta pauta){
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                                                    .ativa(true)
                                                    .pauta(pauta)
                                                    .tempoInicioSessao(LocalDateTime.now())
                                                    .tempoFimSessao(LocalDateTime.now().plusMinutes(1))
                                                    .situacao(Situacao.ABERTA)
                                                    .build();  

        repository.save(sessaoVotacao);
    }

    public Optional<SessaoVotacao> buscarSessaoVotacao(Long id){
        Optional<SessaoVotacao> sessaoVotacao = repository.findById(id);
        
        if(sessaoVotacao.isEmpty()){
            throw new NotFoundException("Não foi possivel encontrar essa sessão de votação");
        }

        return sessaoVotacao;
    }
}