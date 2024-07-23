package desafio.votacao.dto.Pauta;

import desafio.votacao.enums.Categoria;
import jakarta.validation.constraints.NotBlank;

public record RequestPautaDto(

    @NotBlank(message  = "O titulo não pode estar vazio")
    String titulo,

    String descricao,

    @NotBlank(message  = "A categoria não pode estar vazia")
    Categoria categoria,

    Boolean ativaSessao,
    
    int tempoSessao
) {}
