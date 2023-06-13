package com.example.memberboardproject.dto.jyDto;

import com.example.memberboardproject.entity.jyEntity.JyMemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JyMemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private String createdAt;

    private MultipartFile memberProfile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public static JyMemberDTO toDTO(JyMemberEntity jyMemberEntity) {
        JyMemberDTO jyMemberDTO = new JyMemberDTO();
        jyMemberDTO.setId(jyMemberEntity.getId());
        jyMemberDTO.setMemberEmail(jyMemberEntity.getMemberEmail());
        jyMemberDTO.setMemberPassword(jyMemberEntity.getMemberPassword());
        jyMemberDTO.setMemberName(jyMemberEntity.getMemberName());
        jyMemberDTO.setMemberMobile(jyMemberEntity.getMemberMobile());
        jyMemberDTO.setMemberBirth(jyMemberEntity.getMemberBirth());

        if (jyMemberEntity.getFileAttached() == 1) {
            jyMemberDTO.setFileAttached(1);
            jyMemberDTO.setOriginalFileName(jyMemberEntity.getJyMemberFileEntityList().get(0).getOriginalFileName());
            jyMemberDTO.setStoredFileName(jyMemberEntity.getJyMemberFileEntityList().get(0).getStoredFileName());
        } else {
            jyMemberDTO.setFileAttached(0);
        }
        return jyMemberDTO;
    }
}
