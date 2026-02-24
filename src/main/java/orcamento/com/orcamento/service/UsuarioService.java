package orcamento.com.orcamento.service;

import orcamento.com.orcamento.repository.UsuarioRepository;
import orcamento.com.orcamento.usuario.DTO.DadosAtualizarUsuario;
import orcamento.com.orcamento.usuario.DTO.DadosCadastroUsuario;
import orcamento.com.orcamento.usuario.DTO.DadosListarUsuario;
import orcamento.com.orcamento.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository repository;

    @Transactional
    public Usuario cadastrarUsuario (DadosCadastroUsuario dados){
        return repository.save(new Usuario(dados));
    }

    public Page<DadosListarUsuario> listarUsuarios(Pageable page){
        return repository.findAll(page).map(DadosListarUsuario::new);
    }

    public Page<DadosListarUsuario> buscarUsuarioPorLogin( String login, Pageable page){
        return repository.findByLoginContainingIgnoreCase(login,page).map(DadosListarUsuario::new);
    }

    public DadosListarUsuario buscarPorId(Long id){
        return new DadosListarUsuario(repository.getReferenceById(id));
    }

    @Transactional
    public DadosCadastroUsuario atualizarSenha(DadosAtualizarUsuario dados){
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarSenha(dados);
        return new DadosCadastroUsuario(usuario);
    }
    @Transactional public void excluirUsuario(Long id){
        Usuario usuario = repository.getReferenceById(id);
        usuario.setAtivo(false);
    }
}
