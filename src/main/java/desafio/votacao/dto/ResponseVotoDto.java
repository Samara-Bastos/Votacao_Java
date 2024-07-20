package desafio.votacao.dto;

import desafio.votacao.enums.TipoVoto;
import desafio.votacao.model.Voto;

public record ResponseVotoDto(
    TipoVoto tipo,
    ResponseUsuarioDto usuario,
    ResponseSessaoVotacaoDto votacao
) {
    public ResponseVotoDto(Voto voto){
        this(voto.getTipo(), new ResponseUsuarioDto(voto.getUsuario()), new ResponseSessaoVotacaoDto(voto.getVotacao()));
    }
} 

