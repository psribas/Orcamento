package orcamento.com.orcamento.usuario.DTO;

import jakarta.validation.constraints.NotBlank;
import orcamento.com.orcamento.orcamentoObject.OrcamentoEntity;
import orcamento.com.orcamento.usuario.Usuario;

import java.util.List;

public record DadosListarUsuario (

        @NotBlank
        String login,

        List<OrcamentoEntity> orcamentos

){
    public DadosListarUsuario(Usuario usuario){
        this(usuario.getLogin(),usuario.getOrcamentos());
    }

}