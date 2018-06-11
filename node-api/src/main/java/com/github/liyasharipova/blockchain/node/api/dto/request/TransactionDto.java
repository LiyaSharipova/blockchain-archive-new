package com.github.liyasharipova.blockchain.node.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * MiningInfoRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class TransactionDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("hash")
    @NotNull
    private String hash;

    @JsonProperty("uploaded-date-time")
    @NotNull
    private long uploadDateTime;


}

