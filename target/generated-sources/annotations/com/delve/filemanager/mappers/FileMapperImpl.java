package com.delve.filemanager.mappers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-18T10:37:52-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Ubuntu)"
)
@Component
public class FileMapperImpl extends FileMapper {

    @Override
    public FileEntity dtoToEntity(FileDto fileDto) {
        if ( fileDto == null ) {
            return null;
        }

        FileEntity.FileEntityBuilder fileEntity = FileEntity.builder();

        fileEntity.path( fileDto.getPath() );
        fileEntity.fileName( fileDto.getFileName() );

        return fileEntity.build();
    }

    @Override
    public FileDto entityToDto(FileEntity fileEntity) {
        if ( fileEntity == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        fileDto.setPath( fileEntity.getPath() );
        fileDto.setFileName( fileEntity.getFileName() );

        return fileDto;
    }

    @Override
    public FileEntity updateEntity(FileEntity fileEntityOld, FileEntity fileEntityNew) {
        if ( fileEntityOld == null ) {
            return fileEntityNew;
        }

        fileEntityNew.setPath( fileEntityOld.getPath() );
        fileEntityNew.setFileName( fileEntityOld.getFileName() );

        return fileEntityNew;
    }
}
