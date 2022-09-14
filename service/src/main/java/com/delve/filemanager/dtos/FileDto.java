package com.delve.filemanager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

@Data
public class FileDto {

    @Schema(description = "Internal indetify number of file", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Url path", example = "ilike/potatoes/and/pumpkins/")
    private String path;

    @Schema(description = "File name", example = "potatoes.txt")
    private String fileName;

    @JsonIgnore
    @Schema(description = "File content", example = "File to upload in multipartFile format")
    private MultipartFile file;
}
