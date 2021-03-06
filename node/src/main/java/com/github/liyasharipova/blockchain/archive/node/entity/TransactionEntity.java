package com.github.liyasharipova.blockchain.archive.node.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_hash")
    private String fileHash;

    @Column(name = "file_id")
    private Long fileId;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private BlockEntity blockEntity;

    @Column(name = "uploaded_time")
    private Long uploadedTime;

    public TransactionEntity(String fileHash, Long fileId, BlockEntity blockEntity, Long uploadedTime) {
        this.fileHash = fileHash;
        this.fileId = fileId;
        this.blockEntity = blockEntity;
        this.uploadedTime = uploadedTime;
    }

    public TransactionEntity(String fileHash, Long fileId, Long uploadedTime) {
        this.fileHash = fileHash;
        this.fileId = fileId;
        this.uploadedTime = uploadedTime;
    }
}