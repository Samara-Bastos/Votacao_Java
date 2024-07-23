package desafio.votacao.service.SessaoVotacao;

import java.util.Optional;

import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;

public interface SessaoVotacaoService {
    
    void abrirSessaoVotacao(int tempo, Pauta pauta);
    Optional<SessaoVotacao> buscarSessaoVotacao(Long id);
    void contabilizarVotoNaSessao(Long id, RequestVotoDto dto);
    void verificaSeTempoSessaoExpirou(SessaoVotacao sessaoVotacao );
}
