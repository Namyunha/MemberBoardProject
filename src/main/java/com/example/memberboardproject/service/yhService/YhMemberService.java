package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberFileEntity;
import com.example.memberboardproject.repository.yhRepository.YhMemberFileRepository;
import com.example.memberboardproject.repository.yhRepository.YhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YhMemberService {
    private final YhMemberRepository yhMemberRepository;
    private final YhMemberFileRepository yhMemberFileRepository;

    public Long save(YhMemberDTO yhMemberDTO) {
        if (yhMemberDTO.getMemberProfile() == null || yhMemberDTO.getMemberProfile().get(0).isEmpty()) {
            YhMemberEntity yhMemberEntity = YhMemberEntity.toSaveEntity(yhMemberDTO);
            return yhMemberRepository.save(yhMemberEntity).getId();
        } else {
            YhMemberEntity yhMemberEntity = YhMemberEntity.toSaveEntityWithFile(yhMemberDTO);
            System.out.println("서비스에 있는 yhMemberEntity.getMemberName() = " + yhMemberEntity.getMemberName());
            YhMemberEntity savedFile = yhMemberRepository.save(yhMemberEntity);
            for (MultipartFile memberFile : yhMemberDTO.getMemberProfile()) {
                String originalFileName = memberFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                YhMemberFileEntity yhMemberFileEntity = YhMemberFileEntity.toSaveFileEntity(savedFile, originalFileName, storedFileName);
                yhMemberFileRepository.save(yhMemberFileEntity);
            }
            return savedFile.getId();
        }
    }

    public YhMemberDTO login(YhMemberDTO yhMemberDTO) {
        Optional<YhMemberEntity> yhMemberEntity = yhMemberRepository.findByMemberEmailAndMemberPassword(yhMemberDTO.getMemberEmail(), yhMemberDTO.getMemberPassword());
        YhMemberEntity loginMemberEntiy = yhMemberEntity.get();
        if (loginMemberEntiy == null) {
            return null;
        } else {
            return yhMemberDTO;
        }
    }
}
