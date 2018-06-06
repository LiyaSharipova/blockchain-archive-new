package com.github.liyasharipova.blockchain.archive.node.dto.request;

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
public class FileRequest {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("hash")
    @NotNull
    private String hash;

}

