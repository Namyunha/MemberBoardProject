package com.example.memberboardproject.repository.SwRepository;

import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import com.example.memberboardproject.entity.SwEntity.SwMemberFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwMemberFileRepository extends JpaRepository<SwMemberFileEntity,Long> {

}
