package orcamento.com.orcamento.usuario;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import orcamento.com.orcamento.orcamentoObject.OrcamentoEntity;
import orcamento.com.orcamento.usuario.DTO.DadosAtualizarUsuario;
import orcamento.com.orcamento.usuario.DTO.DadosCadastroUsuario;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name="tb_usuario")
@Entity(name="usuario")
@SQLRestriction("ativo=true")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario  {


    public Usuario(DadosCadastroUsuario dados) {
        this.login = dados.login();
        this.senha = dados.senha();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    private boolean ativo=true;

    @NotBlank
    @Size(min = 8, max = 50)
    private String login;

    @Enumerated
    private Role role;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 8, max = 20)
    private String senha;

    @OneToMany(mappedBy = "usuario")
   @JsonIgnore
    private List<OrcamentoEntity> orcamentos = new ArrayList<>();

//    @Override
//    public String getPassword() {
//        return senha;
//    }
//
//    @Override
//    public String getUsername() {
//        return login;
//    }


    public void atualizarSenha (DadosAtualizarUsuario dados){
        this.senha = dados.senha();
    }

}