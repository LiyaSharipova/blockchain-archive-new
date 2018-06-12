package com.github.liyasharipova.blockchain.filestorage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ДТО для отображения на фронте
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Long id;

    private String name;

    private String hash;

    private byte[] data;

    public FileDto(String name, String hash, byte[] data) {
        this.name = name;
        this.hash = hash;
        this.data = data;
    }
}