package com.github.liyasharipova.blockchain.archive.node.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Диапазон nonce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonceRangeDto {

    private Long beginNonce;

    private Long endNonce;
}