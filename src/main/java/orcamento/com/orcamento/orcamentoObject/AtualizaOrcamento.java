package orcamento.com.orcamento.orcamentoObject;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizaOrcamento( @NotNull
                                 Long id,
                                 Integer valor,
                                @Enumerated
                                 Status status,
                                 LocalDate dataalteracao
                                ) {
}
