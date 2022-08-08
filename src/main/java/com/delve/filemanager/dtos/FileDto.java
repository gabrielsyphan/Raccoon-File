package com.delve.filemanager.dtos;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
public class FileDto {

    private String name;

    @NotBlank
    private String path;

    @Nullable
    private String fileName;

    @Nullable
    private String data;
}
