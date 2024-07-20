package desafio.votacao.dto;

import java.time.LocalDateTime;
import desafio.votacao.enums.Situacao;
import desafio.votacao.model.SessaoVotacao;

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
