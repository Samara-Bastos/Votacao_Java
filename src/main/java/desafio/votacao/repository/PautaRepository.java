package desafio.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import desafio.votacao.model.Pauta;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>  {
    Optional<Pauta> findById(Long id);
}
