package com.proyecto.main;

import com.proyecto.main.model.Producto;
import com.proyecto.main.model.Rol;
import com.proyecto.main.model.Usuario;
import com.proyecto.main.repos.RepoProductos;
import com.proyecto.main.repos.RepoUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private RepoUsuarios repoUsuarios;

    @Autowired
    private RepoProductos repoProductos;

    // ABM de productos de un usuario

    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return repoProductos.findAll();
    }

    @GetMapping("/{id}/productos")
    public List<Producto> getProductos(@PathVariable Long id) {
        return repoUsuarios.findById(id).get().getProductos();
    }

    @PostMapping("/{id}/productos")
    public @ResponseBody String addProducto(@PathVariable("id") Long id, @Valid @RequestBody Producto producto) {
        Usuario usuario = repoUsuarios.findById(id).get();
        if (usuario.getRol() == Rol.VENDEDOR){
            usuario.getProductos().add(producto);
            repoUsuarios.save(usuario);
            return "Producto agregado";
        } else {
            return "Un comprador no puede agregar productos";
        }
    }

    @DeleteMapping("/{id}/productos/{nombre}")
    public @ResponseBody String deleteProducto(@PathVariable("id") Long id, @PathVariable("nombre") String nombre) {
        Usuario usuario = repoUsuarios.findById(id).get();
        if (usuario.getRol() == Rol.VENDEDOR){
            usuario.getProductos().removeIf(producto -> producto.getNombre().equals(nombre));
            repoUsuarios.save(usuario);
            return "Producto eliminado";
        } else {
            return "Un comprador no puede eliminar productos";
        }
    }

    @PutMapping("/{id}/productos/{id_producto}")
    public @ResponseBody String updateProducto(@PathVariable("id") Long id, @PathVariable("id_producto") Long id_producto, @Valid @RequestBody Producto producto) {
        Usuario usuario = repoUsuarios.findById(id).get();
        if (usuario.getRol() == Rol.VENDEDOR){
            Producto productoViejo = repoProductos.findById(id_producto).get();
            productoViejo.setNombre(producto.getNombre());
            productoViejo.setPrecioPesos(producto.getPrecio_en_pesos());
            repoProductos.save(productoViejo);
            return "Producto actualizado";
        } else {
            return "Un comprador no puede actualizar productos";
        }
    }

}
