package desafio.votacao.dto.Usuario;

import desafio.votacao.model.Usuario;

public record ResponseUsuarioDto(
    String nome,
    String cpf
) {
    public ResponseUsuarioDto(Usuario usuario){
        this(usuario.getNome(), usuario.getCpf());
    }
}