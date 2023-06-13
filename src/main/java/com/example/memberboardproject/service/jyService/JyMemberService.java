package com.example.memberboardproject.service.jyService;

import com.example.memberboardproject.dto.jyDto.JyMemberDTO;
import com.example.memberboardproject.entity.jyEntity.JyMemberEntity;
import com.example.memberboardproject.entity.jyEntity.JyMemberFileEntity;
import com.example.memberboardproject.repository.jyRepository.JyMemberFileRepository;
import com.example.memberboardproject.repository.jyRepository.JyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JyMemberService {
    private final JyMemberRepository jyMemberRepository;
    private final JyMemberFileRepository jyMemberFileRepository;

    public boolean emailCheck(String memberEmail) {
        Optional<JyMemberEntity> optionalJyMemberEntity = jyMemberRepository.findByMemberEmail(memberEmail);
        if (optionalJyMemberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Long save(JyMemberDTO jyMemberDTO) throws IOException {
        if (jyMemberDTO.getMemberProfile().isEmpty()) {
            // 파일 없음
            JyMemberEntity jyMemberEntity = JyMemberEntity.toSaveEntity(jyMemberDTO);
            return jyMemberRepository.save(jyMemberEntity).getId();
        } else {
            //파일 있음
            JyMemberEntity jyMemberEntity = JyMemberEntity.toSaveEntityWithFile(jyMemberDTO);
            JyMemberEntity savedEntity = jyMemberRepository.save(jyMemberEntity);
            String originalFileName = jyMemberDTO.getMemberProfile().getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\Springboot_github_img\\" + storedFileName;
            jyMemberDTO.getMemberProfile().transferTo(new File(savePath));
            JyMemberFileEntity jyMemberFileEntity = JyMemberFileEntity.toSaveMemberFileEntity(savedEntity, originalFileName, storedFileName);
            jyMemberFileRepository.save(jyMemberFileEntity);
            return savedEntity.getId();
        }
    }
}
