package desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import desafio.votacao.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>  {
    
}
