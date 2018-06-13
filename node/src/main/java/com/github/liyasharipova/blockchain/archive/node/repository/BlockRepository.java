package com.github.liyasharipova.blockchain.archive.node.repository;

import com.github.liyasharipova.blockchain.archive.node.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, String> {
    BlockEntity findFirstByOrderByIdDesc();

    @Modifying
    @Query("delete from BlockEntity b where b.id in ?1")
    void deleteUsersWithIds(List<Long> ids);
}
