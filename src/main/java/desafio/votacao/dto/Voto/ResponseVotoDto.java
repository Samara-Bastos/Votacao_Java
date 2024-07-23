package desafio.votacao.dto.Voto;

import desafio.votacao.dto.SessaoVotacao.ResponseSessaoVotacaoDto;
import desafio.votacao.dto.Usuario.ResponseUsuarioDto;
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

