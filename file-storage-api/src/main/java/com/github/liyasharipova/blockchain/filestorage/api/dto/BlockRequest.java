package com.github.liyasharipova.blockchain.filestorage.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * BlockRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockRequest {

    @JsonProperty("block-number")
    private Integer blockNumber = null;

    @JsonProperty("file-ids")
    private List<Integer> fileIds = new ArrayList<Integer>();

}

