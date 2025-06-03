package orcamento.com.orcamento.orcamentoObject;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Orcamento")
@Table(name = "Tb_orcamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class OrcamentoEntity {

    public OrcamentoEntity( Orcamento dados) {
        this.nome      = dados.nome();
        this.categoria = dados.categoria();
        this.valor     = dados.valor();
        this.status    = dados.status();
        //this.datacriacao = dados.datacriacao();
        //this.id          = dados.id();
    }

    @PrePersist
    private void DataCriacao () {
        this.datacriacao = LocalDate.now();
        this.dataalteracao = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "tb_orcamento_s")
    @SequenceGenerator(name = "tb_orcamento_s", sequenceName = "tb_orcamento_s", allocationSize = 1)
    private Integer id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private int valor;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate datacriacao;
    private LocalDate dataalteracao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void AtualizarOrcamento( AtualizaOrcamento dados) {
        int cont = 0;
        if (dados.valor() != null) {
            this.valor = dados.valor();
            cont++;
        }
        if (dados.status() != null) {
            this.status = dados.status();
            cont++;
        }
        if (cont > 0) {
            this.dataalteracao = LocalDate.now();
        }
    }
}

