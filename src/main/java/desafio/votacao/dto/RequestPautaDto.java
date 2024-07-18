package desafio.votacao.dto;

import desafio.votacao.enums.Categoria;
//import desafio.votacao.model.SessaoVotacao;

public record RequestPautaDto(
    String titulo,
    String descricao,
    Categoria categoria,
    Boolean ativaSessao
) {}
