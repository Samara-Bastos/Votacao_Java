package desafio.votacao.fixture;

import desafio.votacao.dto.Usuario.RequestUsuarioDto;

public class UsuarioDtoFixture {

    public static RequestUsuarioDto usuarioDtoValido(){
        return new RequestUsuarioDto("Carlos", "79905896058");
    }

    public static RequestUsuarioDto usuarioDtoInvalido(){
        return new RequestUsuarioDto("Carlos", null);
    }

}
