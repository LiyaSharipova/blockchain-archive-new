package com.github.liyasharipova.blockchain.archive.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * ДТО для отображения на фронте
 */
@Data
@AllArgsConstructor
public class FileDto {

    private String name;

    private String hash;

    private Resource data;

}