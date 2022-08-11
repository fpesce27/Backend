package com.proyecto.main.model;

import com.proyecto.main.api.entities.Dolar;
import com.proyecto.main.api.service.PrecioDolar;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Entity
public class Producto {
    @Transient
    PrecioDolar precioDolar = new PrecioDolar();
    @Transient
    Dolar dolar = precioDolar.datosDolar();

    public Producto() throws IOException {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
    @NotNull
    private float precio_en_pesos;
    @Transient
    private float precio_en_dolares;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Usuario> usuario;


    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario.add(usuario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio_en_pesos() {
        return precio_en_pesos;
    }

    public void setPrecioPesos(float preciosPesos) {
        this.precio_en_pesos = preciosPesos;
    }

    public float getPrecio_en_dolares() {
        return precio_en_pesos / dolar.compra;
    }

    public void setPrecio_en_dolares() {
        this.precio_en_dolares = this.precio_en_pesos / dolar.compra;
    }
}
