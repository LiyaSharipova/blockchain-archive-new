package com.github.liyasharipova.blockchain.archive.node.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "block")
@Data
public class BlockEntity {
    @Id
    @Column(name = "hash")
    private String hash;

    @Basic
    @Column(name = "previous_hash")
    private String previousHash;
}
