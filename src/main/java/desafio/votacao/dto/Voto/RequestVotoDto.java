package desafio.votacao.dto.Voto;

import desafio.votacao.enums.TipoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestVotoDto(
    @NotNull(message = "O voto precisa ser preenchido")
    TipoVoto tipo,

    @NotBlank(message = "O CPF precisa ser válido e conter apenas numeros")
    String cpf
) {}
