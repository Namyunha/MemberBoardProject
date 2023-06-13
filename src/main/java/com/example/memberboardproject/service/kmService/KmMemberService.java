package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberFileEntity;
import com.example.memberboardproject.repository.kmRepository.KmMemberFileRepository;
import com.example.memberboardproject.repository.kmRepository.KmMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KmMemberService {

    private final KmMemberRepository kmMemberRepository;
    private final KmMemberFileRepository kmMemberFileRepository;

    //    private final KmMemberFileRepository kmMemberFileRepository;
    public Long save(KmMemberDTO kmMemberDTO) throws IOException {

        if (kmMemberDTO.getMemberProfileFile().isEmpty()) {
            KmMemberEntity kmMemberEntity = KmMemberEntity.savetoKmMemberEntity(kmMemberDTO);
            return kmMemberRepository.save(kmMemberEntity).getId();
        } else { //프로필파일이 있다면
            KmMemberEntity kmMemberEntity = KmMemberEntity.savetoKmMemberEntityWithFile(kmMemberDTO);
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

    public List<KmMemberDTO> findAll() {
        List<KmMemberEntity> kmMemberEntities = kmMemberRepository.findAll();
        List<KmMemberDTO> kmMemberDTOList = new ArrayList<>();
        kmMemberEntities.forEach(kmMemberEntity -> {
            kmMemberDTOList.add(KmMemberDTO.toDTO(kmMemberEntity));

        });
        return kmMemberDTOList;
    }


}
