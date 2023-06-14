package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberFileEntity;
import com.example.memberboardproject.repository.kmRepository.KmMemberFileRepository;
import com.example.memberboardproject.repository.kmRepository.KmMemberRepository;
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
public class KmMemberService {

    private final KmMemberRepository kmMemberRepository;
    private final KmMemberFileRepository kmMemberFileRepository;
    @Transactional

    public Long save(KmMemberDTO kmMemberDTO) throws IOException {
        System.out.println("서비스kmMemberDTO = " + kmMemberDTO);
        System.out.println("파일없나"+ kmMemberDTO.getMemberProfileFile().isEmpty());

        if (kmMemberDTO.getMemberProfileFile().isEmpty()) {
            KmMemberEntity kmMemberEntity = KmMemberEntity.saveToKmMemberEntity(kmMemberDTO);
            return kmMemberRepository.save(kmMemberEntity).getId();
        } else { //프로필파일이 있다면
            KmMemberEntity kmMemberEntity = KmMemberEntity.saveToKmMemberEntityWithFile(kmMemberDTO);
            KmMemberEntity kmSavedEntity = kmMemberRepository.save(kmMemberEntity);
            for(MultipartFile kmMemberProfileFile : kmMemberDTO.getMemberProfileFile()){
                String profileOriginalFileName = kmMemberProfileFile.getOriginalFilename();
                String profileStoredFileName = System.currentTimeMillis() + "_" + profileOriginalFileName;
                String savePath = "D:\\Springboot_github_img\\" + profileStoredFileName;
                kmMemberProfileFile.transferTo(new File(savePath));
                KmMemberFileEntity kmMemberFileEntity =
                        KmMemberFileEntity.toSaveKmMemberFileEntity(kmSavedEntity,profileOriginalFileName,profileStoredFileName);
                kmMemberFileRepository.save(kmMemberFileEntity);
            }
            return kmSavedEntity.getId();

        }


    }
    @Transactional
    public List<KmMemberDTO> findAll() {
        List<KmMemberEntity> kmMemberEntities = kmMemberRepository.findAll();
        List<KmMemberDTO> kmMemberDTOList = new ArrayList<>();
        kmMemberEntities.forEach(kmMemberEntity -> {
            kmMemberDTOList.add(KmMemberDTO.toDTO(kmMemberEntity));

        });
        return kmMemberDTOList;
    }


    public int loginChk(KmMemberDTO kmMemberDTO) {
        Optional<KmMemberEntity> kmMemberEntity =  kmMemberRepository.findByMemberEmailAndMemberPass(kmMemberDTO.getMemberEmail(), kmMemberDTO.getMemberPass());
        System.out.println("로그인 되면kmMemberEntity = " + kmMemberEntity);
        if (kmMemberEntity.isPresent()){
            return 1;
        }else{
            return 0;
        }
    }

    public boolean findByEmail(String memberEmail) {
        Optional<KmMemberEntity> kmMemberEntity = kmMemberRepository.findByMemberEmail(memberEmail);
        if (kmMemberEntity.isPresent()){
            return false;
        }else {
            return true;
        }
    }
    @Transactional
    public KmMemberDTO findMemberByEmail(String loginEmail) {
//        Optional<KmMemberEntity> kmMemberEntity = kmMemberRepository.findByMemberEmail(loginEmail);
//        return KmMemberDTO.toDTO(kmMemberEntity.get());
        KmMemberEntity kmMemberEntity = kmMemberRepository.findByMemberEmail(loginEmail).orElseThrow(()-> new NoSuchElementException());
        System.out.println("마이페이지찾을kmMemberEntity = " + kmMemberEntity);
        return KmMemberDTO.toDTO(kmMemberEntity);
    }
}
