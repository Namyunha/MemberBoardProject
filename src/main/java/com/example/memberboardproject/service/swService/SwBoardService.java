package com.example.memberboardproject.service.swService;

import com.example.memberboardproject.dto.SwDTO.SwBoardDTO;
import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.entity.SwEntity.SwBoardEntity;
import com.example.memberboardproject.entity.SwEntity.SwBoardFileEntity;
import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import com.example.memberboardproject.repository.SwRepository.SwBoardFileRepository;
import com.example.memberboardproject.repository.SwRepository.SwBoardRepository;
import com.example.memberboardproject.repository.SwRepository.SwMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SwBoardService {
    private final SwBoardRepository swBoardRepository;
    private final SwBoardFileRepository swBoardFileRepository;
    private final SwMemberRepository swMemberRepository;

    public Long save(SwBoardDTO swBoardDTO) throws IOException {
        SwMemberEntity swMemberEntity = swMemberRepository.findByMemberName(swBoardDTO.getBoardWriter());
        if(swBoardDTO.getSwBoardFile() == null || swBoardDTO.getSwBoardFile().get(0).isEmpty()) {
            return swBoardRepository.save(SwBoardEntity.toSaveEntity(swMemberEntity,swBoardDTO)).getId();
        }else {
            SwBoardEntity swBoardEntity = swBoardRepository.save(SwBoardEntity.toSaveEntity(swMemberEntity,swBoardDTO));
            SwBoardEntity saveBoardEntity = swBoardRepository.save(swBoardEntity);
            for(MultipartFile swBoardFile : swBoardDTO.getSwBoardFile()) {
                String originalFileName = swBoardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis()+"-"+originalFileName;
                String savePath = "D:\\Springboot_github_img\\"+ storedFileName;
                swBoardFile.transferTo(new File(savePath));
                SwBoardFileEntity swBoardFileEntity = SwBoardFileEntity.toSaveBoardFileEntity(saveBoardEntity,originalFileName,storedFileName);
                swBoardFileRepository.save(swBoardFileEntity);
            }
            return saveBoardEntity.getId();
        }
    }
}
