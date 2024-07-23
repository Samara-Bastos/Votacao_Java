package desafio.votacao.dto.Voto;

import desafio.votacao.enums.TipoVoto;

public record RequestVotoDto(
    TipoVoto tipo,
    String cpf
) {}
