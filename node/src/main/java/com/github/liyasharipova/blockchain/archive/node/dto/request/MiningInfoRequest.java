package com.github.liyasharipova.blockchain.archive.node.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * MiningInfoRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class MiningInfoRequest {

    /**
     * Идентификатор блока
     *
     * @return blockId
     **/
    @JsonProperty("block_id")
    @NotNull
    private String blockId;

    /**
     * Найденный nonce
     *
     * @return nonce
     **/
    @JsonProperty("nonce")
    @NotNull
    private Long nonce;

}

