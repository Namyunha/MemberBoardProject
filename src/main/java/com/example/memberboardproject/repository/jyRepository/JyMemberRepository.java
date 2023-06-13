package com.example.memberboardproject.repository.jyRepository;

import com.example.memberboardproject.entity.jyEntity.JyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JyMemberRepository extends JpaRepository<JyMemberEntity, Long> {

    Optional<JyMemberEntity> findByMemberEmail(String memberEmail);
}
