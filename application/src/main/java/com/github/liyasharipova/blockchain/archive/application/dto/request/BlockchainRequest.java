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
 * BlockchainRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class BlockchainRequest {
  @JsonProperty("block-number")
  @NotNull
  private Long blockNumber;

  @JsonProperty("file-hash")
  @NotNull
  private String fileHash;

  @JsonProperty("result")
  @NotNull
  private Boolean result;
}

