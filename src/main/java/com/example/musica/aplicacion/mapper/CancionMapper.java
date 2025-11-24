package com.example.musica.aplicacion.mapper;

import com.example.musica.infraestructura.model.Cancion;
import com.example.musica.aplicacion.dto.CancionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy; // <-- ✅ IMPORTANTE
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CancionMapper {

    CancionMapper INSTANCE = Mappers.getMapper(CancionMapper.class);

    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "album.id", target = "albumId")
    CancionDTO toDto(Cancion cancion);

    @Mapping(source = "artistaId", target = "artista.id")
    @Mapping(source = "albumId", target = "album.id")
    Cancion toEntity(CancionDTO dto);
}
