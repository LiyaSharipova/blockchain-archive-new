package com.github.liyasharipova.blockchain.archive.file.storage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file", schema = "public")
@Data
@NoArgsConstructor
public class FileEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "hash")
    private String hash;

    @Basic
    @Column(name = "data")
    private byte[] data;

    public FileEntity(String name, String hash, byte[] data) {
        this.name = name;
        this.hash = hash;
        this.data = data;
    }
}