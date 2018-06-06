package com.github.liyasharipova.blockchain.archive.file.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FileRequest
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class FileRequest {

    @JsonProperty("file")
    private MultipartFile file = null;

    @JsonProperty("hash")
    private String hash = null;

}

