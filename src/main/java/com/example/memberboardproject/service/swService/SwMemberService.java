package com.example.memberboardproject.service.swService;

import com.example.memberboardproject.dto.SwDTO.SwMemberDTO;
import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import com.example.memberboardproject.entity.SwEntity.SwMemberFileEntity;
import com.example.memberboardproject.repository.SwRepository.SwMemberFileRepository;
import com.example.memberboardproject.repository.SwRepository.SwMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SwMemberService {
    private final SwMemberRepository swMemberRepository;
    private final SwMemberFileRepository swMemberFileRepository;

    public Long save(SwMemberDTO swMemberDTO) throws IOException {
        if(swMemberDTO.getSwMemberFile() == null || swMemberDTO.getSwMemberFile().get(0).isEmpty()) {
            SwMemberEntity swMemberEntity = SwMemberEntity.toSaveEntity(swMemberDTO);
            return swMemberRepository.save(swMemberEntity).getId();
        }else {
            SwMemberEntity swMemberEntity = SwMemberEntity.toSaveWithFileEntity(swMemberDTO);
            SwMemberEntity saveMemberEntity = swMemberRepository.save(swMemberEntity);
            for(MultipartFile swMemberFile : swMemberDTO.getSwMemberFile()) {
                String originalFileName = swMemberFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis()+"-"+originalFileName;
                String savePath = "D:\\Springboot_github_img\\"+ storedFileName;
                swMemberFile.transferTo(new File(savePath));
                SwMemberFileEntity swMemberFileEntity = SwMemberFileEntity.toSaveMemberFileEntity(saveMemberEntity,originalFileName,storedFileName);
                swMemberFileRepository.save(swMemberFileEntity);
            }
            return saveMemberEntity.getId();
        }
    }

    public SwMemberDTO findByEmail(String memberEmail) {
        SwMemberEntity swMemberEntity = swMemberRepository.findByMemberEmail(memberEmail);
        if(swMemberEntity!=null) {
            return SwMemberDTO.toDTO(swMemberEntity);
        }else {
            return null;
        }
    }
    @Transactional
    public SwMemberDTO findByEmailAndMemberPassword(String memberEmail, String memberPassword) {
        SwMemberEntity swMemberEntity = swMemberRepository.findByMemberEmailAndMemberPassword(memberEmail,memberPassword);
        if(swMemberEntity!=null) {
            return SwMemberDTO.toDTO(swMemberEntity);
        }else {
            return null;
        }
    }
    @Transactional
    public SwMemberDTO findById(Long id) {
        SwMemberEntity swMemberEntity = swMemberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return SwMemberDTO.toDTO(swMemberEntity);
    }

    public void memberUpdate(SwMemberDTO swMemberDTO) throws IOException {
        if(swMemberDTO.getSwMemberFile() == null || swMemberDTO.getSwMemberFile().get(0).isEmpty()) {
            swMemberRepository.save(SwMemberEntity.toUpdateEntity(swMemberDTO));
        }else {
            SwMemberEntity saveMemberEntity = swMemberRepository.save(SwMemberEntity.toUpdateWithFileEntity(swMemberDTO));
            for(MultipartFile swMemberFile : swMemberDTO.getSwMemberFile()) {
                String originalFileName = swMemberFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis()+"-"+originalFileName;
                String savePath = "D:\\Springboot_github_img\\"+ storedFileName;
                swMemberFile.transferTo(new File(savePath));
                SwMemberFileEntity swMemberFileEntity = SwMemberFileEntity.toUpdateMemberFileEntity(saveMemberEntity,originalFileName,storedFileName);
                swMemberFileRepository.save(swMemberFileEntity);
            }
        }
    }

    public void memberDelete(Long id) {
        swMemberRepository.deleteById(id);
    }
}
