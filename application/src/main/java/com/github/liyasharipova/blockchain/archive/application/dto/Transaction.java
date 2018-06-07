package com.github.liyasharipova.blockchain.archive.application.dto;

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
public class Transaction {

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

