package com.delve.filemanager.mappers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FileMapper {

    public static final FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "data", ignore = true)
    public abstract FileEntity dtoToEntity(FileDto fileDto);

    @Mapping(target = "data", ignore = true)
    public abstract FileDto entityToDto(FileEntity fileEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract FileEntity updateEntity(FileEntity fileEntityOld, @MappingTarget FileEntity fileEntityNew);
}
