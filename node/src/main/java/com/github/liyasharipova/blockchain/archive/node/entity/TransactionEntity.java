package com.github.liyasharipova.blockchain.archive.node.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "transaction", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "file_hash")
    private String fileHash;

    @Column(name = "file_id")
    private Long fileId;

    @ManyToOne
    @JoinColumn(name = "block_hash")
    private BlockEntity blockHash;

    @Column(name = "uploaded_time")
    private Long uploadedTime;

    public TransactionEntity(String fileHash, Long fileId, BlockEntity blockHash, Long uploadedTime) {
        this.fileHash = fileHash;
        this.fileId = fileId;
        this.blockHash = blockHash;
        this.uploadedTime = uploadedTime;
    }

    public TransactionEntity(String fileHash, Long fileId, Long uploadedTime) {
        this.fileHash = fileHash;
        this.fileId = fileId;
        this.uploadedTime = uploadedTime;
    }
}