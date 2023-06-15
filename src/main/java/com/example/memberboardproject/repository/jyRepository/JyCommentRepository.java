package com.example.memberboardproject.repository.jyRepository;

import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import com.example.memberboardproject.entity.jyEntity.JyCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JyCommentRepository extends JpaRepository<JyCommentEntity, Long> {
    List<JyCommentEntity> findByJyBoardEntityOrderByIdDesc(JyBoardEntity jyBoardEntity);
}
