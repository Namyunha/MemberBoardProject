package com.example.memberboardproject.repository.SwRepository;

import com.example.memberboardproject.entity.SwEntity.SwCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwCommentRepository extends JpaRepository<SwCommentEntity,Long> {
}
