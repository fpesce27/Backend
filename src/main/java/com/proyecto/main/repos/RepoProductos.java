package com.proyecto.main.repos;

import com.proyecto.main.model.Producto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
public interface RepoProductos extends PagingAndSortingRepository<Producto, Long> {
    public Optional<Producto> findById(Long id);
    public List<Producto> findAll();
}
