package com.github.liyasharipova.blockchain.archive.file.storage.repository;

import com.github.liyasharipova.blockchain.archive.file.storage.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends CrudRepository<FileEntity,Long> {
    FileEntity getFileByHash(String fileHash);
}