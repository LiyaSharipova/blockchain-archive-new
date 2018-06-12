package com.github.liyasharipova.blockchain.archive.node.repository;

import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, String> {
}
