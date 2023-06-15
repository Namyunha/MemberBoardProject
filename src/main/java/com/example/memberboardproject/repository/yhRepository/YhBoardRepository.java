package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YhBoardRepository extends JpaRepository<YhBoardEntity, Long> {
    YhCommentEntity findByYhCommentEntityList(Long id);
}
