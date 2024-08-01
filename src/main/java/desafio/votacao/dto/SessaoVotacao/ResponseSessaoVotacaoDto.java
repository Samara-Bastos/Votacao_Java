package desafio.votacao.dto.SessaoVotacao;

import java.time.LocalTime;

import desafio.votacao.enums.Resultado;
import desafio.votacao.enums.Situacao;
import desafio.votacao.model.SessaoVotacao;

public record ResponseSessaoVotacaoDto(
    Long idVotacao,

    Boolean ativa,

    Integer votosSim,

    Integer votosNao,

    LocalTime tempoInicioSessao,

    LocalTime tempoFimSessao,

    Situacao situacao,

    Resultado resultado
) {
    public ResponseSessaoVotacaoDto(SessaoVotacao sessaoVotacao){
        this(sessaoVotacao.getId(), sessaoVotacao.getAtiva(), sessaoVotacao.getVotosSim(), sessaoVotacao.getVotosNao(), sessaoVotacao.getTempoInicioSessao(), sessaoVotacao.getTempoFimSessao(), sessaoVotacao.getSituacao(), sessaoVotacao.getResultado());
    }
}
