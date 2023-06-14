package com.example.memberboardproject.repository.kmRepository;

import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KmBoardRepository extends JpaRepository<KmBoardEntity, Long> {


    }
