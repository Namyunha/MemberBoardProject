package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardFileEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.repository.yhRepository.YhBoardFileRepository;
import com.example.memberboardproject.repository.yhRepository.YhBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public List<YhBoardDTO> findAll() {
        List<YhBoardEntity> yhBoardEntityList = yhBoardRepository.findAll();
        System.out.println("서비스에 있는 yhBoardEntityList = " + yhBoardEntityList);
        List<YhBoardDTO> yhBoardDTOList = new ArrayList<>();
        for (YhBoardEntity boardEntity : yhBoardEntityList) {
            YhBoardDTO yhBoardDTO = YhBoardDTO.toSaveDTO(boardEntity);
            yhBoardDTOList.add(yhBoardDTO);
        }
        return yhBoardDTOList;
    }

    @Transactional
    public YhBoardDTO findById(Long id) {
        YhBoardEntity yhBoardEntity = yhBoardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        YhBoardDTO yhBoardDTO = YhBoardDTO.toSaveDTO(yhBoardEntity);
        return yhBoardDTO;
    }

//    public Long updateBoard(YhBoardDTO yhBoardDTO) {
//        if (yhBoardDTO.getFileAttached() == 1) {
//            List<String> originalFileList = new ArrayList<>();
//            List<String> storedFileList = new ArrayList<>();
//            YhBoardEntity yhBoardEntity = YhBoardEntity.toUpdateWithFile(yhBoardDTO);
//            for (MultipartFile boardFile : yhBoardDTO.getBoardFile()) {
//                String originalFileName = boardFile.getOriginalFilename();
//                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
//                originalFileList.add(originalFileName);
//                storedFileList.add(storedFileName);
//                YhBoardFileEntity yhBoardFileEntity = YhBoardFileEntity.toUpdateFile(yhBoardEntity, originalFileName, storedFileName);
//            }
//            yhBoardDTO.setStoredFileName(originalFileList);
//            yhBoardDTO.setStoredFileName(storedFileList);
//
//        } else {
//
//        }
//        YhBoardEntity.toUpdateWithFile(yhBoardDTO);
//        return null;
//    }

    public Long updateBoard(YhBoardDTO yhBoardDTO) {
        YhBoardEntity yhBoardEntity = YhBoardEntity.toUpdateWithFile(yhBoardDTO);
        return yhBoardRepository.save(yhBoardEntity).getId();
    }

    public void deleteById(Long id) {
        yhBoardRepository.deleteById(id);
    }
}
