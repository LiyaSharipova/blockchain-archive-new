package com.github.liyasharipova.blockchain.archive.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BlockRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class BlockRequest {

    /**
     * Номер блока
     *
     * @return blockNumber
     **/
    @JsonProperty("block-number")
    @NotNull
    private Integer blockNumber = null;

    /**
     * Список идентификаторов файлов в блоке
     *
     * @return fileIds
     **/
    @JsonProperty("file-ids")
    @NotNull
    @Valid
    private List<Integer> fileIds = new ArrayList<Integer>();
}

