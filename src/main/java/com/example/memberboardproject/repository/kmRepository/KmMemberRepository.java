package com.example.memberboardproject.repository.kmRepository;

import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KmMemberRepository extends JpaRepository<KmMemberEntity,Long> {
    Optional<KmMemberEntity> findByMemberEmailAndMemberPass(String memberEmail,String memberPass);

    Optional<KmMemberEntity> findByMemberEmail(String memberEmail);
}
