package com.example.musica.aplicacion.mapper;

import com.example.musica.infraestructura.model.Artista;
import com.example.musica.aplicacion.dto.ArtistaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy; // <-- ✅ IMPORTANTE
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArtistaMapper {

    ArtistaMapper INSTANCE = Mappers.getMapper(ArtistaMapper.class);

    ArtistaDTO toDto(Artista artista);

    Artista toEntity(ArtistaDTO dto);
}
