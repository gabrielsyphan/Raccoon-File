package com.delve.filemanager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class FileDto {

    @NotBlank
    @Schema(description = "Url path", example = "ilike/potatoes/and/pumpkins/")
    private String path;

    @Nullable
    @Schema(description = "File name", example = "potatoes.txt")
    private String fileName;

    @Nullable
    @Schema(description = "File content", example = "File to upload in multipartFile format")
    private MultipartFile file;
}
