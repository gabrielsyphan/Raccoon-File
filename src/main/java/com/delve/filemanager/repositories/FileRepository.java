package com.delve.filemanager.repositories;

import com.delve.filemanager.domains.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query("SELECT f.id, f.name, f.path, f.fileName, f.createdAt, f.updatedAt FROM FileEntity f WHERE f.path = ?1")
    Page<FileEntity> findByPath(String path, Pageable pageable);

    void deleteAllByPath(String path);
}
