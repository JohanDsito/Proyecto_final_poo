package com.example.musica.infraestructura.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "duracion_segundos", nullable = false)
    private Integer duracionSegundos;

    @Column(name = "numero_pista")
    private Integer numeroPista;

    @Column(length = 50)
    private String genero;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "numero_reproducciones")
    private Long numeroReproducciones = 0L;

    @Column(length = 1000)
    private String letra;

    @Column(name = "url_archivo", length = 255)
    private String urlArchivo;

    @Enumerated(EnumType.STRING)
    private EstadoCancion estado = EstadoCancion.ACTIVA;

    @Column(name = "fecha_grabacion")
    private LocalDateTime fechaGrabacion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public Cancion() {}

    public Cancion(String titulo, Integer duracionSegundos, Artista artista) {
        this.titulo = titulo;
        this.duracionSegundos = duracionSegundos;
        this.artista = artista;
    }

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

    public EstadoCancion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCancion estado) {
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }

    // Método auxiliar para obtener duración en formato MM:SS
    public String getDuracionFormateada() {
        int minutos = duracionSegundos / 60;
        int segundos = duracionSegundos % 60;
        return String.format("%d:%02d", minutos, segundos);
    }
}