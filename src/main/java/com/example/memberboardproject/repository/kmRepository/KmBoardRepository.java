package com.example.memberboardproject.repository.kmRepository;

import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KmBoardRepository extends JpaRepository<KmBoardEntity, Long> {

    @Modifying
    @Query(value = "update KmBoardEntity k set k.boardHits=k.boardHits+1 where k.id =:id")
    void updateHits(@Param("id") Long id);

    Page<KmBoardEntity> findByBoardTitleContaining(String q, PageRequest id);
    Page<KmBoardEntity> findByBoardWriterContaining(String q, PageRequest id);

}
