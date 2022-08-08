package com.delve.filemanager.services;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import com.delve.filemanager.mappers.FileMapper;
import com.delve.filemanager.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    public Page<FileEntity> listAll(FileDto fileDto, Pageable pageable) {
        return this.fileRepository.findByPath(fileDto.getPath(), pageable);
    }

    public FileEntity getFile(Long id) {
        return this.fileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("File not found"));
    }

    public FileEntity create(FileDto fileDto) {
        FileEntity fileEntity = FileMapper.INSTANCE.dtoToEntity(fileDto);

        if(fileDto.getData() != null) {
            fileEntity.setData(Base64.getDecoder().decode(fileDto.getData().getBytes()));
        }

        return this.fileRepository.save(fileEntity);
    }

    public FileEntity update(Long id, FileDto fileDto) {
        FileEntity fileEntity = fileRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("File not found")
        );

        FileEntity fileEntityOld = FileMapper.INSTANCE.dtoToEntity(fileDto);

        if(fileDto.getData() != null) {
            fileEntityOld.setData(Base64.getDecoder().decode(fileDto.getData().getBytes()));
        } else {
            fileEntityOld.setData(fileEntity.getData());
        }

        return this.fileRepository.save(FileMapper.INSTANCE.updateEntity(
                fileEntityOld,
                fileEntity
            )
        );
    }

    public void deleteById(Long id) {
        this.fileRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllByPath(FileDto filerDto) {
        this.fileRepository.deleteAllByPath(filerDto.getPath());
    }
}
