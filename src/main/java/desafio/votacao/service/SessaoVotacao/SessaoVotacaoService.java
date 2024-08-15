package desafio.votacao.service.SessaoVotacao;

import java.time.LocalTime;
import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;

public interface SessaoVotacaoService {
    
    void abrirSessaoVotacao(boolean ativaSessao, int tempo, Pauta pauta);
    SessaoVotacao buscarSessaoVotacao(Long id);
    void contabilizarVotoNaSessao(SessaoVotacao sessaoVotacao, RequestVotoDto dto);
    void verificaSeTempoSessaoExpirou(Long id, LocalTime tempoFimSessao);
}
