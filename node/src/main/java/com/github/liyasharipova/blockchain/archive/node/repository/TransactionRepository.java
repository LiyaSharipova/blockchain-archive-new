package com.github.liyasharipova.blockchain.archive.node.repository;

import com.github.liyasharipova.blockchain.archive.node.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findByFileName(String filename);
}
