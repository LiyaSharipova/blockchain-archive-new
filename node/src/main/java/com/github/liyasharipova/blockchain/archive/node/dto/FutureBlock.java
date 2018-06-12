package com.github.liyasharipova.blockchain.archive.node.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Блок для будущего добавления в блокчейн
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FutureBlock {

    /** Добавился ли блок в блокчейн после всех проверок */
    private boolean isAddingSuccessful;

    /** Список хэшей файлов с их идентификаторами в списке храниилищ */
    private List<String> hashes = new ArrayList<>();


}