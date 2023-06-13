package com.example.memberboardproject.repository.hsRepository;

import com.example.memberboardproject.entity.hsEntity.HsFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsFileRepository extends JpaRepository<HsFileEntity,Long> {
}
