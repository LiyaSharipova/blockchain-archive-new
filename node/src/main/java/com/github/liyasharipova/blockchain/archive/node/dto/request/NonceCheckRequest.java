package com.github.liyasharipova.blockchain.archive.node.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * NonceCheckRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NonceCheckRequest {

    @JsonProperty("nonce")
    private Long nonce;

    @JsonProperty("block-hash")
    private String blockHash;

    @JsonProperty("block-number")
    private Long blockNumber;

    @JsonProperty("transactions")
    @Valid
    private List<String> transactions;

}

