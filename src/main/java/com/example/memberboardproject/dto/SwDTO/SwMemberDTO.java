package com.example.memberboardproject.dto.SwDTO;

import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import com.example.memberboardproject.entity.SwEntity.SwMemberFileEntity;
import com.example.memberboardproject.util.SwUtil.SwUtilClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwMemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private int fileAttached;
    private List<MultipartFile> swMemberFile;
    private String CreatedAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static SwMemberDTO toDTO(SwMemberEntity swMemberEntity) {
        SwMemberDTO swMemberDTO = new SwMemberDTO();
        swMemberDTO.setId(swMemberEntity.getId());
        swMemberDTO.setMemberEmail(swMemberEntity.getMemberEmail());
        swMemberDTO.setMemberPassword(swMemberEntity.getMemberPassword());
        swMemberDTO.setMemberName(swMemberEntity.getMemberName());
        swMemberDTO.setMemberMobile(swMemberEntity.getMemberMobile());
        swMemberDTO.setMemberBirth(swMemberEntity.getMemberBirth());
        swMemberDTO.setCreatedAt(SwUtilClass.dateFormat(swMemberEntity.getCreatedAt()));

//         파일 여부에 따른 코드 추가
        if(swMemberEntity.getFileAttached() == 1) {
            swMemberDTO.setFileAttached(1);
            // 파일 이름을 담을 리스트 객체 선언
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            // 첨부파일에 각각 접근
            for(SwMemberFileEntity swMemberFileEntity : swMemberEntity.getSwMemberFileEntityList()) {
                originalFileNameList.add(swMemberFileEntity.getOriginalFileName());
                storedFileNameList.add(swMemberFileEntity.getStoredFileName());
            }
            swMemberDTO.setOriginalFileName(originalFileNameList);
            swMemberDTO.setStoredFileName(storedFileNameList);
        }else {
            swMemberDTO.setFileAttached(0);
        }
        return swMemberDTO;
    }
}