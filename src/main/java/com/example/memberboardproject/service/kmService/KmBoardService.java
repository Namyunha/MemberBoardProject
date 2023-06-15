package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.repository.kmRepository.KmBoardFileRepository;
import com.example.memberboardproject.repository.kmRepository.KmBoardRepository;
import com.example.memberboardproject.repository.kmRepository.KmMemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
}
