package com.github.liyasharipova.blockchain.archive.node.dto;

import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
public class BlockDto {

    /** Добавился ли блок в блокчейн после всех проверок */
    private boolean isAddingSuccessful;

    /**
     * Хэш блока, вычисляюющий на основе хэша пред. блока, merkleRoot (хэш на основе всех транзакций),
     * timeStamp, nonce
     */
    private String hash;

    /** Хэш предыдущего блока */
    private String previousHash;

    /** Хэш, высчитываемый в цикле с помощью конкатенации всех соседних транзакций */
    private String merkleRoot;

    /** Список транзакций в блоке */
    private List<TransactionDto> transactions = new ArrayList<>();

    /** Время создания блока */
    private long timeStamp;

    /** сгенерированное случайно число обеспечивающее сложность хеша */
    private Long nonce = 0L;

    public BlockDto(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    /** Вычисляем новый хэш на основе содержимого блока */
    public String calculateHash() {
        String calculatedhash = StringUtil.applyStribog(
                previousHash +
                        Long.toString(timeStamp) +
                        Long.toString(nonce) +
                        merkleRoot
        );
        return calculatedhash;
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
