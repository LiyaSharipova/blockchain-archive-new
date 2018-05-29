package com.github.liyasharipova.blockchain.archive.file.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ДТО для отображения на фронте
 */
@Data
public class FileDto {

    private String name;

    private String hash;

    private byte[] data;

}