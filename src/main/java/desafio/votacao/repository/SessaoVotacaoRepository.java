package desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import desafio.votacao.model.SessaoVotacao;
import java.util.Optional;
import java.util.List;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>  {
    Optional<SessaoVotacao> findById(Long id);

    @Query("SELECT e FROM SessaoVotacao e WHERE e.ativa = true ")
    List<SessaoVotacao> findBySessaoVotacaoAtiva();

}
