package desafio.votacao.dto;

import java.time.LocalDateTime;
import java.util.List;

import desafio.votacao.enums.Situacao;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Voto;

public record ResponseSessaoVotacaoDto(

    Boolean ativa,

    Integer votosSim,

    Integer votosNao,

    LocalDateTime tempoInicioSessao,

    LocalDateTime tempoFimSessao,

    Situacao situacao
) {
    public ResponseSessaoVotacaoDto(SessaoVotacao sessaoVotacao){
        this(sessaoVotacao.getAtiva(), sessaoVotacao.getVotosSim(), sessaoVotacao.getVotosNao(), sessaoVotacao.getTempoInicioSessao(), sessaoVotacao.getTempoFimSessao(), sessaoVotacao.getSituacao());
    }
}
