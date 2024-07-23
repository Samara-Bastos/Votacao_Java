package desafio.votacao.dto.Voto;

import desafio.votacao.enums.TipoVoto;
import jakarta.validation.constraints.NotBlank;

public record RequestVotoDto(
    @NotBlank(message = "O voto precisa ser preenchido")
    TipoVoto tipo,

    @NotBlank(message = "O CPF precisa ser preenchido")
    String cpf
) {}
