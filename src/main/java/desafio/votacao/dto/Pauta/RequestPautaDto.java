package desafio.votacao.dto.Pauta;

import desafio.votacao.enums.Categoria;

public record RequestPautaDto(
    String titulo,
    String descricao,
    Categoria categoria,
    Boolean ativaSessao,
    int tempoSessao
) {}
