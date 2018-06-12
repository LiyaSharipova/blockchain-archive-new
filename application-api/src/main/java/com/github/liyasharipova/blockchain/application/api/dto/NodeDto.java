package com.github.liyasharipova.blockchain.application.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Данные о каждой ноде
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeDto {

    private String host;

    private String port;
}