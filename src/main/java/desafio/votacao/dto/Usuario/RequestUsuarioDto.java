package desafio.votacao.dto.Usuario;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record RequestUsuarioDto(

    @NotBlank(message = "O nome não pode estar vazio")
    String nome,

    @CPF(message = "O CPF precisa ser válido e conter apenas numeros")
    String cpf
) {}
