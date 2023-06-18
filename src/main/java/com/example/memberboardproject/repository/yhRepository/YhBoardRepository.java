package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhCommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YhBoardRepository extends JpaRepository<YhBoardEntity, Long> {
    YhCommentEntity findByYhCommentEntityList(Long id);

    Page<YhBoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    Page<YhBoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

}
