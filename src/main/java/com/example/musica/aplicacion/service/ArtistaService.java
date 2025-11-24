package com.example.musica.aplicacion.service;

import com.example.musica.aplicacion.dto.ArtistaDTO;
import com.example.musica.aplicacion.mapper.ArtistaMapper;
import com.example.musica.infraestructura.model.Artista;
import com.example.musica.infraestructura.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final ArtistaMapper artistaMapper;

    public ArtistaService(ArtistaRepository artistaRepository, ArtistaMapper artistaMapper) {
        this.artistaRepository = artistaRepository;
        this.artistaMapper = artistaMapper;
    }

    public ArtistaDTO crearArtista(ArtistaDTO dto) {
        Artista artista = artistaMapper.toEntity(dto);
        return artistaMapper.toDto(artistaRepository.save(artista));
    }

    public List<ArtistaDTO> listarArtistas() {
        return artistaRepository.findAll().stream()
                .map(artistaMapper::toDto)
                .collect(Collectors.toList());
    }

    public ArtistaDTO obtenerArtista(Long id) {
        return artistaRepository.findById(id)
                .map(artistaMapper::toDto)
                .orElse(null);
    }

    public void eliminarArtista(Long id) {
        artistaRepository.deleteById(id);
    }
}
