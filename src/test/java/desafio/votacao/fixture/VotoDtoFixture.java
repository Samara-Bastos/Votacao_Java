package desafio.votacao.fixture;

import desafio.votacao.dto.Voto.RequestVotoDto;
import desafio.votacao.enums.TipoVoto;

public class VotoDtoFixture {
    public static RequestVotoDto votoDtoValido(){
        return new RequestVotoDto(TipoVoto.SIM, "79905896058");
    }

    public static RequestVotoDto votoDtoInvalido(){
        return new RequestVotoDto(null, "79905896058");
    }
}
