package desafio.votacao.fixture;

import desafio.votacao.dto.Pauta.RequestPautaDto;
import desafio.votacao.enums.Categoria;

public class PautaDtoFixture {
    
    public static RequestPautaDto PautaDtoValido(){
        return new RequestPautaDto("Java script é uma linguagem de programação ?", 
        "descrição de exemplo ...", 
        Categoria.TECNOLOGIA, 
        true, 
        2);
    }

    public static RequestPautaDto PautaDtoInvalido(){
        return new RequestPautaDto("Java script é uma linguagem de programação ?", 
        "descrição de exemplo ...", 
        Categoria.TECNOLOGIA ,
        true, 
        0);
    }
}
