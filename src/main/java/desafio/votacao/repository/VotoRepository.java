package desafio.votacao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import desafio.votacao.model.Usuario;
import desafio.votacao.model.Voto;


@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByUsuario(Usuario usuario);
}
