package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import com.example.memberboardproject.repository.kmRepository.KmBoardFileRepository;
import com.example.memberboardproject.repository.kmRepository.KmBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KmBoardService {
    public final KmBoardRepository kmBoardRepository;
    public final KmBoardFileRepository kmBoardFileRepository;

    public Long save(KmBoardDTO kmBoardDTO) throws IOException {
        if (kmBoardDTO.getBoardFile().isEmpty()) {
            KmBoardEntity kmBoardEntity = KmBoardEntity.saveToBoardEntity(kmBoardDTO);
            return kmBoardRepository.save(kmBoardEntity).getId();
        } else {
            KmBoardEntity kmBoardEntity = KmBoardEntity.saveToBoardEntityWithFile(kmBoardDTO);
            KmBoardEntity kmSavedBoardEntity = kmBoardRepository.save(kmBoardEntity);

            for (MultipartFile kmBoardFile : kmBoardDTO.getBoardFile()){
                String originalBoardFileName = kmBoardFile.getOriginalFilename();
                String storedBoardFileName = System.currentTimeMillis() + originalBoardFileName;
                String savePath = "D:\\Springboot_github_img\\" + storedBoardFileName;
                kmBoardFile.transferTo(new File(savePath));
                KmBoardFileEntity kmBoardFileEntity =
                        KmBoardFileEntity.saveToBoardFileEntity(kmSavedBoardEntity,originalBoardFileName,storedBoardFileName);
                kmBoardFileRepository.save(kmBoardFileEntity);
            }
            return kmSavedBoardEntity.getId();

        }


    }
}
