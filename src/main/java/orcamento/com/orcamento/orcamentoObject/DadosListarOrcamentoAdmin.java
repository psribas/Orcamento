package orcamento.com.orcamento.orcamentoObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListarOrcamentoAdmin(

        Long id,
        String nome,
        BigDecimal valor,
        Categoria categoria,
        Status status,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao,
        Long usuarioId
) {
    public DadosListarOrcamentoAdmin(OrcamentoEntity orcamento) {
        this(
                orcamento.getId(),
                orcamento.getNome(),
                orcamento.getValor(),
                orcamento.getCategoria(),
                orcamento.getStatus(),
                orcamento.isAtivo(),
                orcamento.getDatacriacao(),
                orcamento.getDataalteracao(),
                orcamento.getUsuario().getId()
        );
    }
}
