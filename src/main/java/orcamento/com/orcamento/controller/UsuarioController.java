package orcamento.com.orcamento.controller;


import jakarta.validation.Valid;
import orcamento.com.orcamento.service.UsuarioService;
import orcamento.com.orcamento.usuario.DTO.DadosAtualizarUsuario;
import orcamento.com.orcamento.usuario.DTO.DadosCadastroUsuario;
import orcamento.com.orcamento.usuario.DTO.DadosListarUsuario;
import orcamento.com.orcamento.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registro")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //@Transactional
    @PostMapping

    public ResponseEntity<Usuario> cadastroUsuario(@RequestBody @Valid DadosCadastroUsuario dados){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{registro}").buildAndExpand(dados.login()).toUri();
        return  ResponseEntity.created(uri).body(service.cadastrarUsuario(dados));
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<DadosListarUsuario>> listarUsuarios (Pageable page){
        return ResponseEntity.ok(service.listarUsuarios(page));
    }

    @GetMapping("/admin/find")
    public ResponseEntity<Page<DadosListarUsuario>> buscarUsuarioPorLogin ( @RequestParam String login,Pageable page){
        return  ResponseEntity.ok(service.buscarUsuarioPorLogin(login,page));
    }


    @GetMapping("/admin/{id:\\d+}")
    public ResponseEntity<DadosListarUsuario> buscarUsuarioPorId (@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/admin")
    public ResponseEntity<DadosCadastroUsuario> alterarSenha (@RequestBody @Valid DadosAtualizarUsuario dados){
        return ResponseEntity.ok(service.atualizarSenha(dados));
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> excluirUsuario (@PathVariable Long id){
        service.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
