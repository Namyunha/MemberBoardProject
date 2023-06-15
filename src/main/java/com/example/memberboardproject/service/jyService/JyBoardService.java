package com.example.memberboardproject.service.jyService;

import com.example.memberboardproject.dto.jyDto.JyBoardDTO;
import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import com.example.memberboardproject.entity.jyEntity.JyBoardFileEntity;
import com.example.memberboardproject.repository.jyRepository.JyBoardFileRepository;
import com.example.memberboardproject.repository.jyRepository.JyBoardRepository;
import com.example.memberboardproject.util.jyUtil.JyUtilClass;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

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

    public Page<JyBoardDTO> paging(Pageable pageable, String type, String q) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5;
        Page<JyBoardEntity> jyBoardEntities = null;
        if (type.equals("title")) {
            jyBoardEntities = jyBoardRepository.findByBoardTitleContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else if (type.equals("writer")) {
            jyBoardEntities = jyBoardRepository.findByBoardWriterContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            jyBoardEntities = jyBoardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        }
        Page<JyBoardDTO> jyBoardDTOS = jyBoardEntities.map(jyBoardEntity -> JyBoardDTO.builder()
                                                            .id(jyBoardEntity.getId())
                                                            .boardTitle(jyBoardEntity.getBoardTitle())
                                                            .boardWriter(jyBoardEntity.getBoardWriter())
                                                            .createdAt(JyUtilClass.dateFormat(jyBoardEntity.getCreatedAt()))
                                                            .boardHits(jyBoardEntity.getBoardHits())
                                                            .build());
        return jyBoardDTOS;
    }

    @Transactional
    public void updateHits(Long id) {
        jyBoardRepository.updateHits(id);
    }

    @Transactional
    public JyBoardDTO findById(Long id) {
        JyBoardEntity jyBoardEntity = jyBoardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return JyBoardDTO.toDTO(jyBoardEntity);
    }
}
