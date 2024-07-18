package desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import desafio.votacao.model.SessaoVotacao;
import java.util.Optional;


public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>  {
    Optional<SessaoVotacao> findById(Long id);
}
