package orcamento.com.orcamento.usuario.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarUsuario(
        Long id,
        @NotBlank
        String senha
) {


}
