package orcamento.com.orcamento.usuario.DTO;

import jakarta.validation.constraints.NotBlank;
import orcamento.com.orcamento.usuario.Usuario;

public record DadosCadastroUsuario (

        @NotBlank
        String login,

        @NotBlank
        String senha
){

    public DadosCadastroUsuario (Usuario usuario){
        this (usuario.getLogin(), usuario.getSenha());
    }
}
