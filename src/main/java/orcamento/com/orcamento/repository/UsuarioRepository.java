package orcamento.com.orcamento.repository;

import orcamento.com.orcamento.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    Usuario findByLogin(String login);

    Page<Usuario> findByLoginContainingIgnoreCase(String login, Pageable page);
}