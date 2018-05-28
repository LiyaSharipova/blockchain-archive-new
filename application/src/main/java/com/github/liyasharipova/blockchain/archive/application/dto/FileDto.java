package com.github.liyasharipova.blockchain.archive.application.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * ДТО для отображения на фронте
 */
@Data
public class FileDto {

    private String name;

    private String hash;

    private Resource data;

}