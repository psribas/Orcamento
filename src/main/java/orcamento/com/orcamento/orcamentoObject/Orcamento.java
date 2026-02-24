package orcamento.com.orcamento.orcamentoObject;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Orcamento(@NotBlank
                         String nome,
                        @Enumerated
                         Categoria categoria,
                        BigDecimal valor,
                        @Enumerated
                         Status status
                        ) {

    public Orcamento( OrcamentoEntity dados) {
        this(dados.getNome(), dados.getCategoria(), dados.getValor(), dados.getStatus());
    }

}
