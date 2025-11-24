package com.example.musica.aplicacion.mapper;

import com.example.musica.infraestructura.model.Album;
import com.example.musica.aplicacion.dto.AlbumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy; 
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    @Mapping(source = "artista.id", target = "artistaId")
    AlbumDTO toDto(Album album);

    @Mapping(source = "artistaId", target = "artista.id")
    Album toEntity(AlbumDTO dto);
}
