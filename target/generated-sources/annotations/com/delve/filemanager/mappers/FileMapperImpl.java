package com.delve.filemanager.mappers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T14:59:20-0300",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class FileMapperImpl extends FileMapper {

    @Override
    public FileEntity dtoToEntity(FileDto fileDto) {
        if ( fileDto == null ) {
            return null;
        }

        FileEntity.FileEntityBuilder fileEntity = FileEntity.builder();

        fileEntity.fileName( fileDto.getFileName() );
        fileEntity.name( fileDto.getName() );
        fileEntity.path( fileDto.getPath() );

        return fileEntity.build();
    }

    @Override
    public FileDto entityToDto(FileEntity fileEntity) {
        if ( fileEntity == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        fileDto.setFileName( fileEntity.getFileName() );
        fileDto.setName( fileEntity.getName() );
        fileDto.setPath( fileEntity.getPath() );

        return fileDto;
    }

    @Override
    public FileEntity updateEntity(FileEntity fileEntityOld, FileEntity fileEntityNew) {
        if ( fileEntityOld == null ) {
            return fileEntityNew;
        }

        byte[] data = fileEntityOld.getData();
        if ( data != null ) {
            fileEntityNew.setData( Arrays.copyOf( data, data.length ) );
        }
        else {
            fileEntityNew.setData( null );
        }
        fileEntityNew.setFileName( fileEntityOld.getFileName() );
        fileEntityNew.setName( fileEntityOld.getName() );
        fileEntityNew.setPath( fileEntityOld.getPath() );

        return fileEntityNew;
    }
}
