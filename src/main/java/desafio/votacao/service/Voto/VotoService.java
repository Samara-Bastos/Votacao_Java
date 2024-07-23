package desafio.votacao.service.Voto;

import java.util.List;

import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.dto.Voto.ResponseVotoDto;

public interface VotoService {
    
    ResponseVotoDto registrarVoto(Long id, RequestVotoDto dto);
    List<ResponseVotoDto> visualizar();
}
