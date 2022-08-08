package com.delve.filemanager.controllers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import com.delve.filemanager.services.FileService;
import com.delve.filemanager.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(Constants.FILE_PATH)
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<Page<FileEntity>> listAll(@RequestBody @Valid FileDto fileDto, Pageable pageable) {
        log.info("listAllFiles() - fileDto: {}", fileDto);
        return ResponseEntity.ok(fileService.listAll(fileDto, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        log.info("getFile() - id: {}", id);
        return ResponseEntity.ok(fileService.getFile(id));
    }

    @PostMapping
    public ResponseEntity<FileEntity> create(@RequestBody @Valid FileDto fileDto) {
        log.info("create() - fileDto: {}", fileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileService.create(fileDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileEntity> update(@PathVariable Long id, @RequestBody @Valid FileDto fileDto) {
        log.info("update() - id: {}, fileDto: {}", id, fileDto);
        return ResponseEntity.ok(this.fileService.update(id, fileDto));
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("deleteFile() - id: {}", id);
        this.fileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllByPath(@RequestBody @Valid FileDto fileDto) {
        log.info("deleteAllByPath()");
        this.fileService.deleteAllByPath(fileDto);
        return ResponseEntity.noContent().build();
    }
}
