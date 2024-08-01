package desafio.votacao.service.Voto;

import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;

public interface VotoService {
    ResponseVotoDto registrarVoto(Long id, RequestVotoDto dto);
}
