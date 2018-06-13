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
import java.util.UUID;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "nonce")
    private Long nonce;

    @Column(name = "previous_hash")
    private String previousHash;

    @OneToMany(mappedBy = "blockEntity")
    private List<TransactionEntity> transactions;

    @Column(name = "number")
    private UUID number;

    public BlockEntity(String hash, Long nonce, String previousHash, UUID number) {
        this.hash = hash;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.number = number;
    }
}