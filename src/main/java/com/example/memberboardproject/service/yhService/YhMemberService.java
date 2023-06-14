package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberFileEntity;
import com.example.memberboardproject.repository.yhRepository.YhMemberFileRepository;
import com.example.memberboardproject.repository.yhRepository.YhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class YhMemberService {
    private final YhMemberRepository yhMemberRepository;
    private final YhMemberFileRepository yhMemberFileRepository;

    public Long save(YhMemberDTO yhMemberDTO) throws IOException {
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
                String savePath = "D:\\Springboot_github_img\\" + storedFileName;
                memberFile.transferTo(new File(savePath));
//                memberFile.transferTo(new File(savePath));
                // 3. BoardFileEntity로 변환 후 board_file_table에 저장
                // 자식 데이터를 저장할 때 반드시 부모의 id가 아닌 부모의 Entity 객체가 전달돼야 함.
                YhMemberFileEntity yhMemberFileEntity = YhMemberFileEntity.toSaveFileEntity(savedFile, originalFileName, storedFileName);
                yhMemberFileRepository.save(yhMemberFileEntity);
            }
            return savedFile.getId();
        }
    }

    public YhMemberDTO login(YhMemberDTO yhMemberDTO) {
        Optional<YhMemberEntity> yhMemberEntity = yhMemberRepository.findByMemberEmailAndMemberPassword(yhMemberDTO.getMemberEmail(), yhMemberDTO.getMemberPassword());
        YhMemberEntity loginMemberEntity = yhMemberEntity.get();
        if (loginMemberEntity == null) {
            return null;
        } else {
            return yhMemberDTO;
        }
    }

    @Transactional
    public YhMemberDTO findByEmail(String loginDTO) {
        YhMemberEntity yhMemberEntity = yhMemberRepository.findByMemberEmail(loginDTO);
        YhMemberDTO yhMemberDTO = YhMemberDTO.toSaveDTO(yhMemberEntity);
        return yhMemberDTO;
    }

    public Long updateUser(YhMemberDTO yhMemberDTO) {
        YhMemberEntity yhMemberEntity = YhMemberEntity.toUpdateEntity(yhMemberDTO);
        return yhMemberRepository.save(yhMemberEntity).getId();
    }

    public void deleteUser(Long id) {
        yhMemberRepository.deleteById(id);
    }

//    public YhMemberDTO findByEmail(String loginDTO) {
//        Optional<YhMemberEntity> yhMemberEntity = yhMemberRepository.find
//    }
}
