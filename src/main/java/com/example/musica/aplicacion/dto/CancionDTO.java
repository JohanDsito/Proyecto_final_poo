package com.example.musica.aplicacion.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CancionDTO {

    private Long id;
    private String titulo;
    private Integer duracionSegundos;
    private Integer numeroPista;
    private String genero;
    private BigDecimal precio;
    private Long numeroReproducciones;
    private String letra;
    private String urlArchivo;
    private String estado;
    private LocalDateTime fechaGrabacion;
    private LocalDateTime fechaRegistro;
    private Long artistaId;
    private Long albumId;

    public CancionDTO() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(Integer duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public Integer getNumeroPista() {
        return numeroPista;
    }

    public void setNumeroPista(Integer numeroPista) {
        this.numeroPista = numeroPista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getNumeroReproducciones() {
        return numeroReproducciones;
    }

    public void setNumeroReproducciones(Long numeroReproducciones) {
        this.numeroReproducciones = numeroReproducciones;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaGrabacion() {
        return fechaGrabacion;
    }

    public void setFechaGrabacion(LocalDateTime fechaGrabacion) {
        this.fechaGrabacion = fechaGrabacion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Long artistaId) {
        this.artistaId = artistaId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
