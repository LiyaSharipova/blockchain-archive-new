package com.github.liyasharipova.blockchain.node.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

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

