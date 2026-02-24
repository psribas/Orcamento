package orcamento.com.orcamento.orcamentoObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListarOrcamento(
        Long id,
        String nome,
        BigDecimal valor,
        Categoria categoria,
        Status status,
        LocalDateTime dataCriacao,
        LocalDateTime dataAlteracao
) {
    public DadosListarOrcamento(OrcamentoEntity orcamento) {
        this(
                orcamento.getId(),
                orcamento.getNome(),
                orcamento.getValor(),
                orcamento.getCategoria(),
                orcamento.getStatus(),
                orcamento.getDatacriacao(),
                orcamento.getDataalteracao()
        );
    }
}
