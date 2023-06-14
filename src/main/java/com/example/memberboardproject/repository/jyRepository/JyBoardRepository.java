package com.example.memberboardproject.repository.jyRepository;

import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JyBoardRepository extends JpaRepository<JyBoardEntity, Long> {
    Page<JyBoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    Page<JyBoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

}
