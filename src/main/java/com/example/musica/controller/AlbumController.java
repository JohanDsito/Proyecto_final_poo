package com.example.musica.controller;

import com.example.musica.aplicacion.dto.AlbumDTO;
import com.example.musica.aplicacion.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> listarAlbums() {
        return ResponseEntity.ok(albumService.listarAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> obtenerAlbum(@PathVariable Long id) {
        AlbumDTO dto = albumService.obtenerAlbum(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> crearAlbum(@RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.crearAlbum(albumDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlbum(@PathVariable Long id) {
        albumService.eliminarAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
