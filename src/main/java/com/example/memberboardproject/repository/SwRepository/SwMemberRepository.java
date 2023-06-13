package com.example.memberboardproject.repository.SwRepository;

import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwMemberRepository extends JpaRepository<SwMemberEntity,Long> {

    SwMemberEntity findByMemberEmail(String memberEmail);
}
