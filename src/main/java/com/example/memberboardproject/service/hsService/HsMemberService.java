package com.example.memberboardproject.service.hsService;

import com.example.memberboardproject.dto.hsDto.HsMemberDTO;
import com.example.memberboardproject.entity.hsEntity.HsFileEntity;
import com.example.memberboardproject.entity.hsEntity.HsMemberEntity;
import com.example.memberboardproject.repository.hsRepository.HsFileRepository;
import com.example.memberboardproject.repository.hsRepository.HsMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HsMemberService {
    private final HsMemberRepository hsMemberRepository;
    private final HsFileRepository hsFileRepository;

    public void save(HsMemberDTO hsMemberDTO) throws IOException {
        if (hsMemberDTO.getMemberFile() == null || hsMemberDTO.getMemberFile().isEmpty()) {
            hsMemberRepository.save(HsMemberEntity.toSaveEntity(hsMemberDTO));
        } else {
            hsMemberDTO.setFileAttached(1);
            HsMemberEntity hsMemberEntity = HsMemberEntity.toSaveEntity(hsMemberDTO);
            HsMemberEntity savedEntity = hsMemberRepository.save(hsMemberEntity);
            String originalFileName = hsMemberDTO.getMemberFile().getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
            String savePath = "D:\\springboot_img\\" + storedFileName;
            hsMemberDTO.getMemberFile().transferTo(new File(savePath));
            HsFileEntity hsFileEntity = HsFileEntity.toSaveFileMemberEntity(savedEntity, originalFileName, storedFileName);
            hsFileRepository.save(hsFileEntity);
        }
    }

    public boolean findByMemberEmail(String memberEmail) {
        Optional<HsMemberEntity> optionalHsMemberEntity = hsMemberRepository.findByMemberEmail(memberEmail);
        if (optionalHsMemberEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
