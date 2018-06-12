package com.github.liyasharipova.blockchain.archive.node.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfCheckResultDto {
    Boolean isCheckSuccessful;
    Long blockNumber;
}
