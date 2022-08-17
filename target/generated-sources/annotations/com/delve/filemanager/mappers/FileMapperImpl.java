package com.delve.filemanager.mappers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:46:41-0300",
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

        fileEntity.name( fileDto.getName() );
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

        fileDto.setName( fileEntity.getName() );
        fileDto.setPath( fileEntity.getPath() );
        fileDto.setFileName( fileEntity.getFileName() );

        return fileDto;
    }

    @Override
    public FileEntity updateEntity(FileEntity fileEntityOld, FileEntity fileEntityNew) {
        if ( fileEntityOld == null ) {
            return fileEntityNew;
        }

        fileEntityNew.setName( fileEntityOld.getName() );
        fileEntityNew.setPath( fileEntityOld.getPath() );
        fileEntityNew.setFileName( fileEntityOld.getFileName() );
        byte[] data = fileEntityOld.getData();
        if ( data != null ) {
            fileEntityNew.setData( Arrays.copyOf( data, data.length ) );
        }
        else {
            fileEntityNew.setData( null );
        }

        return fileEntityNew;
    }
}
