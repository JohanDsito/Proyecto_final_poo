package com.example.musica.controller;

import com.example.musica.aplicacion.dto.ArtistaDTO;
import com.example.musica.aplicacion.service.ArtistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> listarArtistas() {
        return ResponseEntity.ok(artistaService.listarArtistas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> obtenerArtista(@PathVariable Long id) {
        ArtistaDTO dto = artistaService.obtenerArtista(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> crearArtista(@RequestBody ArtistaDTO artistaDTO) {
        return ResponseEntity.ok(artistaService.crearArtista(artistaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Long id) {
        artistaService.eliminarArtista(id);
        return ResponseEntity.noContent().build();
    }
}
