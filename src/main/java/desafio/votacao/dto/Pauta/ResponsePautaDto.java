package desafio.votacao.dto.Pauta;

import desafio.votacao.dto.SessaoVotacao.ResponseSessaoVotacaoDto;
import desafio.votacao.enums.Categoria;
import desafio.votacao.model.Pauta;

public record ResponsePautaDto(
    String titulo,
    String descricao,
    Categoria categoria,
    ResponseSessaoVotacaoDto votacao
) {
    public ResponsePautaDto(Pauta pauta){
        this(pauta.getTitulo(), 
             pauta.getDescricao(), 
             pauta.getCategoria(), 
             pauta.getVotacao() != null ? new ResponseSessaoVotacaoDto(pauta.getVotacao()) : null
        );
    }
} 
