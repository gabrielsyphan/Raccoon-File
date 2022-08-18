package com.delve.filemanager.controllers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import com.delve.filemanager.services.FileService;
import com.delve.filemanager.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(Constants.FILE_PATH)
public class FileController {

    private final FileService fileService;

    @GetMapping
    @Operation(summary = "List all file paths", description = "You just need to send the path to list all files inside it")
    public ResponseEntity<Page<FileEntity>> listAll(
            @RequestBody @Valid FileDto fileDto,
            @ParameterObject Pageable pageable
    ) {
        log.info("listAllFiles() - fileDto: {}", fileDto);
        return ResponseEntity.ok(fileService.listAll(fileDto, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get file by id")
    public ResponseEntity<FileDto> getFile(@PathVariable Long id, HttpServletRequest request) throws IOException {
        log.info("getFile() - id: {}", id);
        FileDto file = fileService.getFile(id);
        file.setPath("http://" + request.getLocalName() + file.getPath());
        return ResponseEntity.ok(file);
    }

    @PostMapping
    @Operation(summary = "Save new file on path")
    public ResponseEntity<FileEntity> create(@ModelAttribute @Valid FileDto fileDto) throws IOException {
        log.info("create() - fileDto: {}", fileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileService.create(fileDto));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update file by id")
    public ResponseEntity<FileEntity> update(@PathVariable Long id, @ModelAttribute @Valid FileDto fileDto) throws IOException {
        log.info("update() - id: {}, fileDto: {}", id, fileDto);
        return ResponseEntity.ok(this.fileService.update(id, fileDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete file by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("deleteFile() - id: {}", id);
        this.fileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all files inside path",
            description = "You just need to send the path to delete all files inside it")
    public ResponseEntity<Void> deleteAllByPath(@RequestBody @Valid FileDto fileDto) throws IOException {
        log.info("deleteAllByPath()");
        this.fileService.deleteAllByPath(fileDto);
        return ResponseEntity.noContent().build();
    }
}
