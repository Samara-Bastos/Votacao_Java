package desafio.votacao.dto.Pauta;

import desafio.votacao.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPautaDto(

    @NotBlank(message  = "O titulo não pode estar vazio")
    String titulo,

    String descricao,

    @NotNull(message  = "A categoria não pode estar vazia")
    Categoria categoria,

    Boolean ativaSessao,
    
    int tempoSessao
) {}
