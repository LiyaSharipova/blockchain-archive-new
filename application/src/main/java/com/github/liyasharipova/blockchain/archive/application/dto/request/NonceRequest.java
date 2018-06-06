package com.github.liyasharipova.blockchain.archive.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    private Integer nodeId = null;

    /**
     * Идентификатор блока для добавления в блокчейн
     *
     * @return blockId
     **/
    @ApiModelProperty(required = true, value = "Идентификатор блока для добавления в блокчейн")
    @NotNull
    @JsonProperty("block_id")
    private Long blockId = null;
}

