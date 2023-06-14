package com.example.memberboardproject.dto.yhdDto;


import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberFileEntity;
import com.example.memberboardproject.util.yhUtil.YhUtilClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class YhMemberDTO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberMobile;
    private String memberBirth;
    private String createdAt;
    private int fileAttached;

    private List<MultipartFile> memberProfile;

    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static YhMemberDTO toSaveDTO(YhMemberEntity yhMemberEntity) {
        YhMemberDTO yhMemberDTO = new YhMemberDTO();
        yhMemberDTO.setId(yhMemberEntity.getId());
        yhMemberDTO.setMemberName(yhMemberEntity.getMemberName());
        yhMemberDTO.setMemberEmail(yhMemberEntity.getMemberEmail());
        yhMemberDTO.setMemberPassword(yhMemberEntity.getMemberPassword());
        yhMemberDTO.setMemberMobile(yhMemberEntity.getMemberMobile());
        yhMemberDTO.setMemberBirth(yhMemberEntity.getMemberBirth());
        yhMemberDTO.setCreatedAt(YhUtilClass.dateFormat(yhMemberEntity.getCreatedAt()));
        // 파일 여부에 따른 코드 추가
        if (yhMemberEntity.getFileAttached() == 1) {
            yhMemberDTO.setFileAttached(1);
            // 파일 이름을 담을 리스트 객체 선언
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            // 첨부파일에 각각 접근
            for (YhMemberFileEntity boardFileEntity : yhMemberEntity.getYhMemberFileEntityList()) {
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }
            yhMemberDTO.setOriginalFileName(originalFileNameList);
            yhMemberDTO.setStoredFileName(storedFileNameList);
        } else {
            yhMemberDTO.setFileAttached(0);
        }
        return yhMemberDTO;
    }

}





