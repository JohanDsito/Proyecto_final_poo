package com.example.musica.controller;

import com.example.musica.aplicacion.dto.CancionDTO;
import com.example.musica.aplicacion.service.CancionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public ResponseEntity<List<CancionDTO>> listarCanciones() {
        return ResponseEntity.ok(cancionService.listarCanciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CancionDTO> obtenerCancion(@PathVariable Long id) {
        CancionDTO dto = cancionService.obtenerCancion(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CancionDTO> crearCancion(@RequestBody CancionDTO cancionDTO) {
        return ResponseEntity.ok(cancionService.crearCancion(cancionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Long id) {
        cancionService.eliminarCancion(id);
        return ResponseEntity.noContent().build();
    }
}
