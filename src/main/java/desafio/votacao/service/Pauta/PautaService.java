package desafio.votacao.service.Pauta;

import java.util.List;
import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.dto.Pauta.ResponsePautaDto;

public interface PautaService {
    
    ResponsePautaDto create(RequestPautaDto dto);
    List<ResponsePautaDto> visualizar();
    ResponsePautaDto visualizarPautaSelecionada( Long id);
}
