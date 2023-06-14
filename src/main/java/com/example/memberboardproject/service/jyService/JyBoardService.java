package com.example.memberboardproject.service.jyService;

import com.example.memberboardproject.dto.jyDto.JyBoardDTO;
import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import com.example.memberboardproject.entity.jyEntity.JyBoardFileEntity;
import com.example.memberboardproject.repository.jyRepository.JyBoardFileRepository;
import com.example.memberboardproject.repository.jyRepository.JyBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JyBoardService {
    private final JyBoardRepository jyBoardRepository;
    private final JyBoardFileRepository jyBoardFileRepository;

    @Transactional
    public Long save(JyBoardDTO jyBoardDTO) throws IOException {
        if (jyBoardDTO.getBoardFile() == null || jyBoardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일 없음
            JyBoardEntity jyBoardEntity = JyBoardEntity.toSaveEntity(jyBoardDTO);
            return jyBoardRepository.save(jyBoardEntity).getId();
        } else {
            // 파일 있음
            JyBoardEntity jyBoardEntity = JyBoardEntity.toSaveEntityWithFile(jyBoardDTO);
            JyBoardEntity savedEntity = jyBoardRepository.save(jyBoardEntity);
            for (MultipartFile boardFile: jyBoardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                String savePath = "D:\\Springboot_github_img\\" + storedFileName;
                boardFile.transferTo(new File(savePath));
                JyBoardFileEntity jyBoardFileEntity =
                        JyBoardFileEntity.toSaveBoardFileEntity(savedEntity, originalFileName, storedFileName);
                jyBoardFileRepository.save(jyBoardFileEntity);
            }
            return savedEntity.getId();
        }
    }
}
