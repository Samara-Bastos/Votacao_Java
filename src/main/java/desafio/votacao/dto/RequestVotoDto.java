package desafio.votacao.dto;

import desafio.votacao.enums.TipoVoto;

public record RequestVotoDto(
    TipoVoto tipo,
    String cpf
) {}
