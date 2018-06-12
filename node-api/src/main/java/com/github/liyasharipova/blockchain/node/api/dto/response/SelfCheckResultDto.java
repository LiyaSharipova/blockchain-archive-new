package com.github.liyasharipova.blockchain.node.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfCheckResultDto {
    Boolean isCheckSuccessful;
    Long blockNumber;
    Long length;
}
