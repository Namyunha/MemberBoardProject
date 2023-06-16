package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YhBoardFileRepository extends JpaRepository<YhBoardFileEntity, Long> {
    YhBoardFileEntity deleteYhBoardFileEntityByYhBoardEntity(Long id);


    YhBoardFileEntity deleteByYhBoardEntity(YhBoardEntity yhBoardEntity);

    YhBoardFileEntity findByYhBoardEntity(YhBoardEntity yhBoardEntity);
}
