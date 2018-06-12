package com.github.liyasharipova.blockchain.archive.node.dto;

import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.UUID;

@Data
@Slf4j
public class BlockDto {

    private UUID uuid;

    /**
     * Добавился ли блок в блокчейн после всех проверок
     */
    private boolean isAddingSuccessful;

    /**
     * Хэш блока, вычисляюющий на основе хэша пред. блока, merkleRoot (хэш на основе всех транзакций),
     * timeStamp, nonce
     */
    private String hash;

    /**
     * Хэш предыдущего блока
     */
    private String previousHash;

    /**
     * Хэш, высчитываемый в цикле с помощью конкатенации всех соседних транзакций
     */
    private String merkleRoot;

    /**
     * Список транзакций в блоке
     */
    private LinkedList<TransactionDto> transactions = new LinkedList<>();

    /**
     * сгенерированное случайно число обеспечивающее сложность хеша
     */
    private Long nonce = 0L;

    public BlockDto() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Добавляем транзакцию к блоку
     */
    public boolean addTransaction(TransactionDto transaction) {
        //если блок не пустой, проверяем валидна ли транзакция
        if (transaction == null) {
            return false;
        }

        transactions.add(transaction);
        return true;
    }

    public void increaseNonce() {
        nonce++;
    }

}
