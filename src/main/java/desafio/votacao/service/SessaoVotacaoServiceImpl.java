package desafio.votacao.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.votacao.enums.Situacao;
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
}
