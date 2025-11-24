package com.example.musica.aplicacion.service;

import com.example.musica.aplicacion.dto.CancionDTO;
import com.example.musica.aplicacion.mapper.CancionMapper;
import com.example.musica.infraestructura.model.Cancion;
import com.example.musica.infraestructura.model.Album;
import com.example.musica.infraestructura.model.Artista;
import com.example.musica.infraestructura.repository.CancionRepository;
import com.example.musica.infraestructura.repository.AlbumRepository;
import com.example.musica.infraestructura.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;
    private final CancionMapper cancionMapper;
    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;

    public CancionService(CancionRepository cancionRepository, CancionMapper cancionMapper,
                          ArtistaRepository artistaRepository, AlbumRepository albumRepository) {
        this.cancionRepository = cancionRepository;
        this.cancionMapper = cancionMapper;
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
    }

    public CancionDTO crearCancion(CancionDTO dto) {
        if (dto.getArtistaId() == null) {
            throw new RuntimeException("El artistaId es obligatorio");
        }

        Cancion cancion = cancionMapper.toEntity(dto);

        Artista artista = artistaRepository.findById(dto.getArtistaId())
                .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID: " + dto.getArtistaId()));
        cancion.setArtista(artista);

        if (dto.getAlbumId() != null) {
            Album album = albumRepository.findById(dto.getAlbumId())
                    .orElseThrow(() -> new RuntimeException("Album no encontrado con ID: " + dto.getAlbumId()));
            cancion.setAlbum(album);
        }

        return cancionMapper.toDto(cancionRepository.save(cancion));
    }

    public List<CancionDTO> listarCanciones() {
        return cancionRepository.findAll().stream()
                .map(cancionMapper::toDto)
                .collect(Collectors.toList());
    }

    public CancionDTO obtenerCancion(Long id) {
        return cancionRepository.findById(id)
                .map(cancionMapper::toDto)
                .orElse(null);
    }

    public void eliminarCancion(Long id) {
        cancionRepository.deleteById(id);
    }
}
