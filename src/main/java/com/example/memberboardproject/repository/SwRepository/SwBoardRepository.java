package com.example.memberboardproject.repository.SwRepository;

import com.example.memberboardproject.entity.SwEntity.SwBoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SwBoardRepository extends JpaRepository<SwBoardEntity,Long> {
}
