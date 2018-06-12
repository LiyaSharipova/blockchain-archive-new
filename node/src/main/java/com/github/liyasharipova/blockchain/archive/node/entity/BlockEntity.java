package com.github.liyasharipova.blockchain.archive.node.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "block", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockEntity {

    @Id
    @Column(name = "hash")
    private String hash;

    @Column(name = "nonce")
    private Long nonce;

    @Column(name = "previous_hash")
    private String previousHash;

    @OneToMany(mappedBy = "blockHash")
    private List<TransactionEntity> transactions;

    public BlockEntity(String hash, Long nonce, String previousHash) {
        this.hash = hash;
        this.nonce = nonce;
        this.previousHash = previousHash;
    }
}