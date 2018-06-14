package com.github.liyasharipova.blockchain.archive.node.service;

import com.github.liyasharipova.blockchain.node.api.dto.request.TransactionDto;
import com.github.liyasharipova.blockchain.node.api.dto.response.BlockDto;
import com.github.liyasharipova.blockchain.archive.node.dto.SuccessfulMinedByOthersBlocks;
import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import com.github.liyasharipova.blockchain.archive.node.entity.TransactionEntity;
import com.github.liyasharipova.blockchain.archive.node.repository.BlockRepository;
import com.github.liyasharipova.blockchain.archive.node.repository.TransactionRepository;
import com.github.liyasharipova.blockchain.archive.node.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с блоком
 */
@Service
@Slf4j
@Transactional
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    TransactionRepository transactionRepository;

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
            transactionRepository.save(transactionEntity);
        });
        blockRepository.save(blockEntity);
    }

    /**
     * Проверка наличия транзакций блока thisBlock среди транзакций из замайненных блоков
     * {@link SuccessfulMinedByOthersBlocks#getSuccessfulBlocks()}
     */
    public boolean isThisBlockInSuccessfulBlocks(BlockDto thisBlock) {
        // Список хешей транзакций
        // в виде строк у проверяемого блока
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
            successfulBlock.getTransactions().forEach(successfulTransaction -> {
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
        String rootHash = "0";
        // Если не будет найден блок, то хэш предыдущего = 0
        // Если будет найден, то отдается через BlockEntity::getHash
        return Optional.ofNullable(blockRepository.findFirstByOrderByIdDesc())
                .map(BlockEntity::getHash)
                .orElse(rootHash);
    }

    public List<BlockDto> getAllBlockDtos() {
        List<BlockEntity> allBlocks = blockRepository.findAll();
        List<BlockDto> blockDtos = new ArrayList<>();
        allBlocks.forEach(blockEntity -> blockDtos.add(toDto(blockEntity)));
        return blockDtos;
    }


    /**
     *
     * @param blocksToCopy все блоки ноды наибольшей длины
     */
    public void reWriteBlocks(List<BlockDto> blocksToCopy) {
        List<BlockDto> allBlockDtos = getAllBlockDtos();
        List<Long> idsToReWright = new ArrayList<>();
        // если появилось неравенство хешей, то все послеующие добавляем в список  на удаление
        boolean isMistakeApears = false;
        for (int i = 0; i < allBlockDtos.size() - 1; i++) {
            if (!blocksToCopy.get(i).getHash().equals(allBlockDtos.get(i).getHash()) || isMistakeApears) {
                idsToReWright.add(allBlockDtos.get(i).getId());
                isMistakeApears = true;
            }
        }
        //удаляем все неверные записи из бд
        blockRepository.deleteBlockWithIds(idsToReWright);
        //из блоков на копирование убираем все оставшиеся верные записи
        blocksToCopy.removeAll(getAllBlockDtos());
        //сохраняем оставшиеся верные записи
        blocksToCopy.forEach(blockDto -> blockRepository.save(toEntity(blockDto)));
    }

    public BlockDto toDto(BlockEntity entity) {
        BlockDto blockDto = new BlockDto();
        blockDto.setId(entity.getId());
        blockDto.setHash(entity.getHash());
        blockDto.setNonce(entity.getNonce());
        blockDto.setPreviousHash(entity.getPreviousHash());
        entity.getTransactions().forEach(trEntity -> {
            TransactionDto transactionDto = new TransactionDto(trEntity.getId(),
                    trEntity.getFileHash(), trEntity.getUploadedTime());
            blockDto.getTransactions().add(transactionDto);
        });
        blockDto.setMerkleRoot(StringUtil.getMerkleRoot(blockDto.getTransactions()));
        return blockDto;
    }

    public BlockEntity toEntity(BlockDto dto) {
        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setId(dto.getId());
        blockEntity.setHash(dto.getHash());
        blockEntity.setNonce(dto.getNonce());
        blockEntity.setPreviousHash(dto.getPreviousHash());
        dto.getTransactions().forEach(trDto -> {
            TransactionEntity transactionEntity = new TransactionEntity(trDto.getHash(), trDto.getId(),
                    blockEntity, trDto.getUploadDateTime());
            blockEntity.getTransactions().add(transactionEntity);
        });
        return blockEntity;

    }

    public synchronized void saveBlockIfNotExist(BlockDto blockToAdd) {
        for (BlockEntity blockEntity : blockRepository.findAll()) {
            ArrayList<String> existingTransactions = new ArrayList<>();
            blockEntity.getTransactions().forEach(transactionEntity -> {
                existingTransactions.add(transactionEntity.getFileHash());
            });

            ArrayList<String> toAddTransactions = new ArrayList<>();

            blockToAdd.getTransactions().forEach(transactionDto -> {
                toAddTransactions.add(transactionDto.getHash());
            });

            //todo на далекое будущее -- убрать коллизию всех цепочек за раз
            if (toAddTransactions.containsAll(existingTransactions)) {
                return;
            }
        }
        saveBlock(blockToAdd);
    }
}