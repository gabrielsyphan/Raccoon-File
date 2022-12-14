package com.delve.filemanager.controllers;

import com.delve.filemanager.domains.FileEntity;
import com.delve.filemanager.dtos.FileDto;
import com.delve.filemanager.services.FileService;
import com.delve.filemanager.util.constants.PathConstant;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(PathConstant.FILE_PATH)
public class FileController {

    private final FileService fileService;

    public static final String SERVER_URL = "http://raccoonbit.com";

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
        FileDto file = fileService.getById(id);
        file.setPath(SERVER_URL + "/" + file.getPath());
        return ResponseEntity.ok(file);
    }

    @PostMapping
    @Operation(summary = "Save new file on path")
    public ResponseEntity<FileDto> create(@ModelAttribute @Valid FileDto fileDto) throws IOException {
        log.info("create() - fileDto: {}", fileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileService.create(fileDto));
    }

    @Operation(summary = "Update file by id")
    public ResponseEntity<FileDto> update(@ModelAttribute @Valid FileDto fileDto) throws IOException {
        log.info("update() - fileDto: {}", fileDto);
        return ResponseEntity.ok(this.fileService.update(fileDto));
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
        this.fileService.deleteAll(fileDto);
        return ResponseEntity.noContent().build();
    }
}
