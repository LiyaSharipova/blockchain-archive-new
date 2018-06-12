package com.github.liyasharipova.blockchain.application.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * NonceRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NonceRequest {

    /**
     * Идентификатор блока для добавления в блокчейн
     *
     * @return blockUuid
     **/
    @NotNull
    @JsonProperty("block_Uuid")
    private UUID blockUuid;
}

