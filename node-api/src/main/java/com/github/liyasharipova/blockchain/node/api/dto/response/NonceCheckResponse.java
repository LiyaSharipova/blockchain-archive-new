package com.github.liyasharipova.blockchain.node.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * NonceCheckResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NonceCheckResponse {

    /**
     * Проверка nonce оказалась ли успешной на данной ноде
     *
     * @return isNonceCheckSuccessful
     **/
    @JsonProperty("isNonceCheckSuccessful")
    private Boolean isNonceCheckSuccessful;

    /**
     * Номер блока, для которого пришедший nonce был верен или не верен
     *
     * @return blockNumber
     **/
    @JsonProperty("blockNumber")
    private Integer blockNumber;
}

