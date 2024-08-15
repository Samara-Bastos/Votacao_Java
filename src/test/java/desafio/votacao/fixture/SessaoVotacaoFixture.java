package desafio.votacao.fixture;

import java.time.LocalTime;

import desafio.votacao.enums.Situacao;
import desafio.votacao.model.SessaoVotacao;

public class SessaoVotacaoFixture {
    public static SessaoVotacao sessaoVotacaoValida(){
        return SessaoVotacao.builder()
                            .idVotacao(1L)
                            .ativa(true)
                            .votosSim(3)
                            .votosNao(1)
                            .tempoInicioSessao(LocalTime.of(10,00))
                            .tempoFimSessao(LocalTime.of(10,02))
                            .situacao(Situacao.ABERTA)
                            .build();
    }
}
