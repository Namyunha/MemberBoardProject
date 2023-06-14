package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardFileEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.repository.yhRepository.YhBoardFileRepository;
import com.example.memberboardproject.repository.yhRepository.YhBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YhBoardService {
    private final YhBoardRepository yhBoardRepository;
    private final YhBoardFileRepository yhBoardFileRepository;

    public Long boardSave(YhBoardDTO yhBoardDTO) throws IOException {
        System.out.println("서비스에 있는 yhBoardDTO = " + yhBoardDTO);
        if (yhBoardDTO.getBoardFile() == null || yhBoardDTO.getBoardFile().get(0).isEmpty()) {
            YhBoardEntity yhBoardEntity = YhBoardEntity.toSaveEntity(yhBoardDTO);
            return yhBoardRepository.save(yhBoardEntity).getId();
        } else {
            YhBoardEntity yhBoardEntity = YhBoardEntity.toSaveEntityWithFile(yhBoardDTO);
            YhBoardEntity saveFile = yhBoardRepository.save(yhBoardEntity);
            for (MultipartFile boardFile : yhBoardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                String savePath = "D:\\Springboot_github_img\\" + storedFileName;
                boardFile.transferTo(new File(savePath));
                YhBoardFileEntity yhBoardFileEntity = YhBoardFileEntity.toSaveFileEntity(yhBoardEntity, originalFileName, storedFileName);
                yhBoardFileRepository.save(yhBoardFileEntity);
            }
            return saveFile.getId();
        }
    }
}
