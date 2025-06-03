package orcamento.com.orcamento.orcamentoObject;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Contract;

import java.time.LocalDate;

public record Orcamento(@NotBlank
                         String nome,
                        @Enumerated
                         Categoria categoria,
                        int valor,
                        @Enumerated
                         Status status
                        ) {

    public Orcamento( OrcamentoEntity dados) {
        this(dados.getNome(), dados.getCategoria(), dados.getValor(), dados.getStatus());
    }

}
