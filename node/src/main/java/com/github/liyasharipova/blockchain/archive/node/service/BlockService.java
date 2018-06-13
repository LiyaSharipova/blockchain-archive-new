package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.archive.node.dto.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.SuccessfulMinedByOthersBlocks;
import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import com.github.liyasharipova.blockchain.archive.node.entity.TransactionEntity;
import com.github.liyasharipova.blockchain.archive.node.repository.BlockRepository;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с блоком
 */
@Service
@Slf4j
@Transactional
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    /**
     * Вычисляем новый хэш на основе содержимого блока
     */
    public String calculateHash(BlockDto blockDto) {
        return StringUtil.applyStribog(
                blockDto.getPreviousHash() +
                        Long.toString(blockDto.getNonce()) +
                        blockDto.getMerkleRoot()
        );
    }

    public void saveBlock(BlockDto blockDto) {
        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setHash(blockDto.getHash());
        blockEntity.setNonce(blockDto.getNonce());
        blockEntity.setPreviousHash(blockDto.getPreviousHash());
        blockDto.getTransactions().forEach(transactionDto -> {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setFileHash(transactionDto.getHash());
            transactionEntity.setUploadedTime(transactionDto.getUploadDateTime());
            transactionEntity.setBlockEntity(blockEntity);
            blockEntity.getTransactions().add(transactionEntity);
        });
        blockRepository.save(blockEntity);
    }

    /**
     * Проверка наличия транзакций блока thisBlock среди транзакций из замайненных блоков
     * {@link SuccessfulMinedByOthersBlocks#getSuccessfulBlocks()}
     */
    public boolean isThisBlockInSuccessfulBlocks(BlockDto thisBlock) {
        // Список хешей транзакций в виде строк у проверяемого блока
        ArrayList<String> thisBlockTransactions = new ArrayList<>();
        // Из проверяемого блока вытаскиваем транзакции и кладем каждую в thisBlockTransactions
        thisBlock.getTransactions().forEach(transactionDto -> {
            thisBlockTransactions.add(transactionDto.getHash());
        });

        List<BlockDto> successfulBlocks = SuccessfulMinedByOthersBlocks.getSuccessfulBlocks();
        for (BlockDto successfulBlock : successfulBlocks) {
            // Список хешей транзакций в виде строк у каждого замайненного блока
            ArrayList<String> successfulTransactions = new ArrayList<>();

            // Из каждого замайненного блока вытаскиваем транзакции
            // и кладем каждую в successfulTransactions
            successfulBlock.getTransactions().forEach(successfulTransaction->{
                successfulTransactions.add(successfulTransaction.getHash());
            });

            // Проверяем совпадение всех хешей
            if (successfulTransactions.containsAll(thisBlockTransactions)) {
                // Если совпали, то отдаем успешный ответ,
                // если нет, то првоеряем другие
                return true;
            }
        }

        // Если не нашлось совпадение, а все списки транзакций были проверены,
        // то отдаем, что thisBlock не находится в списке замайненных транзакций
        return false;
    }

    public String getLastBlockHash() {
        return blockRepository.findFirstByOrderByIdDesc().getHash();
    }
}