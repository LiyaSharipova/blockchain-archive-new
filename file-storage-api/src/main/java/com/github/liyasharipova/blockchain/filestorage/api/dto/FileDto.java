package com.github.liyasharipova.blockchain.filestorage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для отображения на фронте
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String name;

    private String hash;

    private byte[] data;

}