package orcamento.com.orcamento.orcamentoObject;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizaOrcamento( @NotNull
                                 Long id,
                                 BigDecimal valor,
                                @Enumerated
                                 Status status,
                                 LocalDateTime dataalteracao
                                ) {
}
