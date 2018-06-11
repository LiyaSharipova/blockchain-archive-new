package com.github.liyasharipova.blockchain.archive.node.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "file_hash")
    private String fileHash;

    @Column(name = "block_hash")

    private String blockHash;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private long createTime;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_data")
    private byte[] fileData;
}
