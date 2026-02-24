package orcamento.com.orcamento.orcamentoObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import orcamento.com.orcamento.usuario.Usuario;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Orcamento")
@Table(name = "Tb_orcamento")

@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("ativo=true")
@EqualsAndHashCode(of = "id")

public class OrcamentoEntity {

    public OrcamentoEntity( Orcamento dados) {
        this.ativo     = true;
        this.nome      = dados.nome();
        this.categoria = dados.categoria();
        this.valor     = dados.valor();
        this.status    = dados.status();
        //this.datacriacao = dados.datacriacao();
        //this.id          = dados.id();
    }

//    @PrePersist
//    private void DataCriacao () {
//        this.datacriacao = LocalDate.now();
//        this.dataalteracao = LocalDate.now();
//    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "tb_orcamento_s")
    @SequenceGenerator(name = "tb_orcamento_s", sequenceName = "tb_orcamento_s", allocationSize = 1)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    private LocalDateTime datacriacao;
    @LastModifiedDate
    private LocalDateTime dataalteracao;
    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id" , nullable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;

    public LocalDateTime getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(LocalDateTime datacriacao) {
        this.datacriacao = datacriacao;
    }

    public LocalDateTime getDataalteracao() {
        return dataalteracao;
    }

    public void setDataalteracao(LocalDateTime dataalteracao) {
        this.dataalteracao = dataalteracao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void AtualizarOrcamento( AtualizaOrcamento dados) {
       // int cont = 0;
        if (dados.valor() != null) {
            this.valor = dados.valor();
           // cont++;
        }
        if (dados.status() != null) {
            this.status = dados.status();
           // cont++;
        }
      //
    }
}

