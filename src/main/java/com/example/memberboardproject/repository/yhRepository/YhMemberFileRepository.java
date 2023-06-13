package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.entity.yhEntity.YhMemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YhMemberFileRepository extends JpaRepository<YhMemberFileEntity, Long> {
}
