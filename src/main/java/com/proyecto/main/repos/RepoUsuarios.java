package com.proyecto.main.repos;

import com.proyecto.main.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController("/usuarios")
public interface RepoUsuarios extends JpaRepository<Usuario, Long> {
    public Usuario deleteByEmail(String email);
    public Usuario save(Usuario usuario);
}
