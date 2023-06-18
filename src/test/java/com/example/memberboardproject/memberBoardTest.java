package com.example.memberboardproject;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.repository.kmRepository.KmBoardRepository;
import com.example.memberboardproject.repository.yhRepository.YhBoardRepository;
import com.example.memberboardproject.service.kmService.KmBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
class memberBoardTest {
    @Autowired
    private KmBoardService kmBoardService;
    @Autowired
    private KmBoardRepository kmBoardRepository;
    @Autowired
    private YhBoardRepository yhBoardRepository;

    @Test
    void contextLoads() {
    }

    private KmBoardDTO newBoard(int i) {
        KmBoardDTO kmBoardDTO = new KmBoardDTO();
        kmBoardDTO.setBoardTitle("title" + i);
        kmBoardDTO.setBoardWriter("writer" + i);
        kmBoardDTO.setBoardContents("contents" + i);
        kmBoardDTO.setBoardCreatedAt("202306" + i);
        kmBoardDTO.setBoardHits(0);
        return kmBoardDTO;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("DB에 데이터 붓기")
    public void saveList() {  // entity없이 test
        IntStream.rangeClosed(1, 50).forEach(i -> {
            kmBoardRepository.save(KmBoardEntity.saveToBoardEntity(newBoard(i), null));
        });
    }

    private YhBoardDTO yhBoardDTO(int i) {
        YhBoardDTO yhBoardDTO = new YhBoardDTO();
        yhBoardDTO.setBoardWriter("Writer" + i);
        yhBoardDTO.setBoardTitle("Title" + i);
        yhBoardDTO.setBoardContents("Contents" + i);
        yhBoardDTO.setBoardHits(0);
        return yhBoardDTO;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("DB에 붓기")
    public void yhSaveList() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            yhBoardRepository.save(YhBoardEntity.toSaveEntity(yhBoardDTO(i)));
        });
    }


}
