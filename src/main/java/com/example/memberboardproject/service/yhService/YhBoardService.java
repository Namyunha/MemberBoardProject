package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardFileEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.repository.yhRepository.YhBoardFileRepository;
import com.example.memberboardproject.repository.yhRepository.YhBoardRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.CurrencyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Long updateBoard(YhBoardDTO yhBoardDTO) throws IOException {
        if (yhBoardDTO.getBoardFile() == null || yhBoardDTO.getBoardFile().get(0).isEmpty()) {
            YhBoardEntity yhBoardEntity = YhBoardEntity.toUpdateEntity(yhBoardDTO);
            YhBoardEntity saveFile = yhBoardRepository.save(yhBoardEntity);
            return saveFile.getId();
        } else {
            YhBoardEntity yhBoardEntity = YhBoardEntity.toUpdateWithFile(yhBoardDTO);
            yhBoardRepository.save(yhBoardEntity);
            for (MultipartFile boardFile : yhBoardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                String savePath = "D:\\Springboot_github_img\\" + storedFileName;
                boardFile.transferTo(new File(savePath));
                YhBoardFileEntity yhBoardFileEntity = YhBoardFileEntity.toSaveFileEntity(yhBoardEntity, storedFileName, originalFileName);
                yhBoardFileRepository.save(yhBoardFileEntity);
            }
            return yhBoardEntity.getId();
        }
    }

    public void deleteById(Long id) {
        yhBoardRepository.deleteById(id);
    }

    @Transactional
    public void deleteFile(Long id) {
        YhBoardEntity yhBoardEntity = yhBoardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        List<YhBoardFileEntity> yhBoardFileEntityList = yhBoardEntity.getYhBoardFileEntityList();
        for (YhBoardFileEntity yhBoardFileEntity : yhBoardFileEntityList) {
            yhBoardFileRepository.deleteById(yhBoardFileEntity.getId());
        }
    }


    public Page<YhBoardDTO> findPage(Pageable pageable, String type, String q) {
        int page = pageable.getPageNumber() - 1;
        int limit = 10;
        Page<YhBoardEntity> yhBoardEntities = null;
        System.out.println("type" + type);
        System.out.println("q" + q);
        if (type.equals("제목")) {
            yhBoardEntities = yhBoardRepository.findByBoardTitleContaining(q, PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id")));
        } else if (type.equals("작성자")) {
            yhBoardEntities = yhBoardRepository.findByBoardWriterContaining(q, PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            yhBoardEntities = yhBoardRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id")));
        }
        Page<YhBoardDTO> yhBoardDTOS = yhBoardEntities.map(yhBoardEntity -> YhBoardDTO
                .builder()
                .id(yhBoardEntity.getId())
                .boardTitle(yhBoardEntity.getBoardTitle())
                .boardWriter(yhBoardEntity.getBoardWriter())
                .boardContents(yhBoardEntity.getBoardContents())
                .boardHits(yhBoardEntity.getBoardHits())
                .build());
        return yhBoardDTOS;
    }
}







