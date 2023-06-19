package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.repository.kmRepository.KmBoardFileRepository;
import com.example.memberboardproject.repository.kmRepository.KmBoardRepository;
import com.example.memberboardproject.repository.kmRepository.KmMemberRepository;
import com.example.memberboardproject.util.kmUtil.KmUtilClass;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KmBoardService {
    public final KmBoardRepository kmBoardRepository;
    public final KmBoardFileRepository kmBoardFileRepository;
    public final KmMemberRepository kmMemberRepository;

    @Transactional
    public Long save(KmBoardDTO kmBoardDTO, String loginEmail) throws IOException {

        KmMemberEntity kmMemberEntity = kmMemberRepository.findByMemberEmail(loginEmail).orElseThrow(() -> new NoSuchElementException());
        if (kmBoardDTO.getBoardFile().isEmpty()) {
            KmBoardEntity kmBoardEntity = KmBoardEntity.saveToBoardEntity(kmBoardDTO, kmMemberEntity);
            return kmBoardRepository.save(kmBoardEntity).getId();
        } else {
            KmBoardEntity kmBoardEntity = KmBoardEntity.saveToBoardEntityWithFile(kmBoardDTO, kmMemberEntity);
            KmBoardEntity kmSavedBoardEntity = kmBoardRepository.save(kmBoardEntity);

            for (MultipartFile kmBoardFile : kmBoardDTO.getBoardFile()) {
                String originalBoardFileName = kmBoardFile.getOriginalFilename();
                String storedBoardFileName = System.currentTimeMillis() + originalBoardFileName;
                String savePath = "D:\\Springboot_github_img\\" + storedBoardFileName;
                kmBoardFile.transferTo(new File(savePath));
                KmBoardFileEntity kmBoardFileEntity =
                        KmBoardFileEntity.saveToBoardFileEntity(kmSavedBoardEntity, originalBoardFileName, storedBoardFileName);
                kmBoardFileRepository.save(kmBoardFileEntity);
            }
            return kmSavedBoardEntity.getId();

        }


    }

    @Transactional
    public List<KmBoardDTO> findAll() {

        List<KmBoardEntity> kmBoardEntityList = kmBoardRepository.findAll();
        List<KmBoardDTO> kmBoardDTOList = new ArrayList<>();
        for (KmBoardEntity be : kmBoardEntityList) {
            KmBoardDTO kmBoardDTO = KmBoardDTO.toDTO(be);
            kmBoardDTOList.add(kmBoardDTO);
        }

        return kmBoardDTOList;

    }

    @Transactional
    public KmBoardDTO findById(Long id) {
        Optional<KmBoardEntity> byId = kmBoardRepository.findById(id);
        KmBoardEntity boardEntity = byId.get();
        KmBoardDTO boardDTO = KmBoardDTO.toDTO(boardEntity);
        return boardDTO;
    }

    @Transactional
    public void boardHits(Long id) {
//        KmBoardEntity kmBoardEntity = KmBoardEntity.updateToBoardEntity(kmBoardDTO);
        kmBoardRepository.updateHits(id);
    }

    @Transactional
    public Page<KmBoardDTO> paging(Pageable pageable, String type, String q) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 화면에 5개 글씩 보겠다.
        Page<KmBoardEntity> kmBoardEntities = null;
        if (type.equals("title")) {
            kmBoardEntities = kmBoardRepository.findByBoardTitleContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else if (type.equals("writer")) {
            kmBoardEntities = kmBoardRepository.findByBoardWriterContaining(q, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            kmBoardEntities = kmBoardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        }
        Page<KmBoardDTO> kmBoardDTOS = kmBoardEntities.map(kmBoardEntity -> KmBoardDTO.builder()
                .id(kmBoardEntity.getId())
                .boardTitle(kmBoardEntity.getBoardTitle())
                .boardWriter(kmBoardEntity.getBoardWriter())
                .boardCreatedAt(KmUtilClass.kmDateFormat(kmBoardEntity.getKmCreatedAt()))
                .build());
        return kmBoardDTOS;
    }
}
