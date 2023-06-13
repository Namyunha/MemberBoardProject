package com.example.memberboardproject.repository.kmRepository;

import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KmMemberRepository extends JpaRepository<KmMemberEntity,Long> {
}
