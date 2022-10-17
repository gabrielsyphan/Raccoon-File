package com.delve.filemanager.services;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import com.delve.filemanager.mappers.FileMapper;
import com.delve.filemanager.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
@Log4j2
public class FileService implements GenericService<FileDto, FileEntity> {

    public static final String rootPath = "/projetos/apache2-docker-angular/www/files";

    private final FileRepository fileRepository;

    @Override
    public Page<FileEntity> listAll(FileDto fileDto, Pageable pageable) {
        return this.fileRepository.findByPath(fileDto.getPath(), pageable);
    }

    @Override
    public FileDto getById(Long id) {
        FileEntity file = this.fileRepository.findById(id).orElseThrow(() -> {
            throw  new IllegalArgumentException("File not found");
        });

        FileDto fileDto = FileMapper.INSTANCE.entityToDto(file);
        String path = this.generatePath(fileDto.getPath()) + fileDto.getFileName();
        path = path.split("/www/")[1];
        fileDto.setPath(path);

        return fileDto;
    }

    @Override
    public FileDto create(FileDto fileDto) throws IOException {
        try {
            FileEntity fileEntity = FileMapper.INSTANCE.dtoToEntity(fileDto);

            String path = this.generatePath(fileDto.getPath());

            if(new File(path + fileDto.getFileName()).exists()) {
                throw new IllegalArgumentException("File already exists");
            }

            log.info("create() - fileDto: {}", fileDto);

            if(fileDto.getFile() != null) {
                Files.copy(fileDto.getFile().getInputStream(), Paths.get(path + fileDto.getFileName()));
            }

            return FileMapper.INSTANCE.entityToDto(this.fileRepository.save(fileEntity));
        } catch (IOException e) {
            log.error("create() - error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public FileDto update(FileDto fileDto) throws IOException {
        try {
            FileEntity fileFound = this.fileRepository.findById(fileDto.getId()).orElseThrow(() -> {
                throw  new IllegalArgumentException("File not found");
            });

            String pathEntity = this.generatePath(fileFound.getPath()) + fileFound.getFileName();
            Files.delete(Paths.get(pathEntity));

            FileEntity fileEntityNew = FileMapper.INSTANCE.updateEntity(
                    FileMapper.INSTANCE.dtoToEntity(fileDto),
                    fileFound
            );

            String pathDto = this.generatePath(fileDto.getPath()) + fileDto.getFileName();
            Files.copy(fileDto.getFile().getInputStream(), Paths.get(pathDto));

            return FileMapper.INSTANCE.entityToDto(this.fileRepository.save(fileEntityNew));
        } catch (Exception e) {
            log.error("update() - error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            FileEntity file = this.fileRepository.findById(id).orElseThrow(() -> {
                throw  new IllegalArgumentException("File not found");
            });

            String path = this.generatePath(file.getPath()) + file.getFileName();
            Files.delete(Paths.get(path));
            this.fileRepository.deleteById(id);
        } catch (Exception e) {
            log.error("deleteById() - error: {}", e.getMessage());
            throw new IllegalArgumentException("File not found");
        }
    }

    @Override
    @Transactional
    public void deleteAll(FileDto filerDto) throws IOException {
        String path = this.generatePath(filerDto.getPath());
        FileUtils.deleteDirectory(new File(path));

        this.fileRepository.deleteAllByPath(filerDto.getPath());
    }

    private String generatePath(String path) {
        if(path.charAt(0) != '/') {
            path = "/" + path;
        }

        if(path.charAt(path.length() - 1) != '/') {
            path = path + "/";
        }

        path = rootPath + path;

        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }

        return path;
    }
}
