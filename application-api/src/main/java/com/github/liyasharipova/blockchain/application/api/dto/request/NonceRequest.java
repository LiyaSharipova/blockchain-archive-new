package com.github.liyasharipova.blockchain.application.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * NonceRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NonceRequest {

    /**
     * Идентификатор узла
     *
     * @return nodeId
     **/
    @JsonProperty("node_id")
    @NotNull
    private Integer nodeId;

    /**
     * Идентификатор блока для добавления в блокчейн
     *
     * @return blockId
     **/
    @NotNull
    @JsonProperty("block_id")
    private Long blockId;
}

