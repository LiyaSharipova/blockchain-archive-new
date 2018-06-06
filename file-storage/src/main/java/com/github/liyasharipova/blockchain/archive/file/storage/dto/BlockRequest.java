package com.github.liyasharipova.blockchain.archive.file.storage.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BlockRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-06T22:22:34.640+03:00")

public class BlockRequest {
  @JsonProperty("block-number")
  private Integer blockNumber = null;

  @JsonProperty("file-ids")
  @Valid
  private List<Integer> fileIds = new ArrayList<Integer>();

  public BlockRequest blockNumber(Integer blockNumber) {
    this.blockNumber = blockNumber;
    return this;
  }

  /**
   * Номер блока
   * @return blockNumber
  **/
  @ApiModelProperty(required = true, value = "Номер блока")
  @NotNull


  public Integer getBlockNumber() {
    return blockNumber;
  }

  public void setBlockNumber(Integer blockNumber) {
    this.blockNumber = blockNumber;
  }

  public BlockRequest fileIds(List<Integer> fileIds) {
    this.fileIds = fileIds;
    return this;
  }

  public BlockRequest addFileIdsItem(Integer fileIdsItem) {
    this.fileIds.add(fileIdsItem);
    return this;
  }

  /**
   * Список идентификаторов файлов в блоке
   * @return fileIds
  **/
  @ApiModelProperty(required = true, value = "Список идентификаторов файлов в блоке")
  @NotNull


  public List<Integer> getFileIds() {
    return fileIds;
  }

  public void setFileIds(List<Integer> fileIds) {
    this.fileIds = fileIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlockRequest blockRequest = (BlockRequest) o;
    return Objects.equals(this.blockNumber, blockRequest.blockNumber) &&
        Objects.equals(this.fileIds, blockRequest.fileIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockNumber, fileIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlockRequest {\n");
    
    sb.append("    blockNumber: ").append(toIndentedString(blockNumber)).append("\n");
    sb.append("    fileIds: ").append(toIndentedString(fileIds)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

