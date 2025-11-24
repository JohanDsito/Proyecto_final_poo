package com.example.musica.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class AlbumDTO {

    private Long id;
    private String titulo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;

    private String genero;
    private Long artistaId;

    public AlbumDTO() {
    }

    public AlbumDTO(Long id, String titulo, LocalDate fechaLanzamiento, String genero, Long artistaId) {
        this.id = id;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.artistaId = artistaId;
    }

    // Getters y Setters
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

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Long artistaId) {
        this.artistaId = artistaId;
    }
}
