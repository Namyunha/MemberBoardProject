package com.example.memberboardproject.repository.hsRepository;

import com.example.memberboardproject.entity.hsEntity.HsMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HsMemberRepository extends JpaRepository<HsMemberEntity,Long> {

    Optional<HsMemberEntity> findByMemberEmail(String memberEmail);
}
