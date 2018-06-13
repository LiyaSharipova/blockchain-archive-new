package com.github.liyasharipova.blockchain.application.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
     * @return number
     **/
    @JsonProperty("block-number")
    @NotNull
    private Integer blockNumber;

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

