package com.example.musica.aplicacion.service;

import com.example.musica.aplicacion.dto.AlbumDTO;
import com.example.musica.aplicacion.mapper.AlbumMapper;
import com.example.musica.infraestructura.model.Album;
import com.example.musica.infraestructura.model.Artista;
import com.example.musica.infraestructura.repository.AlbumRepository;
import com.example.musica.infraestructura.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final AlbumMapper albumMapper;

    public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.albumMapper = albumMapper;
    }

    public AlbumDTO crearAlbum(AlbumDTO dto) {
        if (dto.getArtistaId() == null) {
            throw new RuntimeException("El artistaId es obligatorio");
        }

        Album album = albumMapper.toEntity(dto);
        Artista artista = artistaRepository.findById(dto.getArtistaId())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID: " + dto.getArtistaId()));
        album.setArtista(artista);

        Album guardado = albumRepository.save(album);
        return albumMapper.toDto(guardado);
    }

    public List<AlbumDTO> listarAlbums() {
        return albumRepository.findAll().stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toList());
    }

    public AlbumDTO obtenerAlbum(Long id) {
        return albumRepository.findById(id)
                .map(albumMapper::toDto)
                .orElse(null);
    }

    public void eliminarAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
