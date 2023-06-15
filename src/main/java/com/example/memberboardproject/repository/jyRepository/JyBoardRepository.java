package com.example.memberboardproject.repository.jyRepository;

import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JyBoardRepository extends JpaRepository<JyBoardEntity, Long> {
    Page<JyBoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    Page<JyBoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

    @Modifying
    @Query(value = "update JyBoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
