package orcamento.com.orcamento.orcamentoObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastroOrcamentoAdmin(

        @NotBlank
        String nome,

        @NotNull
        BigDecimal valor,

        @NotNull
        Categoria categoria,

        @NotNull
        Status status,

        @NotNull
        Long usuarioId
){


    public CadastroOrcamentoAdmin(OrcamentoEntity orcamento) {
        this(   orcamento.getNome(),
                orcamento.getValor(),
                orcamento.getCategoria(),
                orcamento.getStatus(),
                orcamento.getUsuario().getId()
        );
    }
}
