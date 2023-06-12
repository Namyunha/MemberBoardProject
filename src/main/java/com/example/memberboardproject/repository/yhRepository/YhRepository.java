package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YhRepository extends JpaRepository<YhMemberEntity, Long> {

}
