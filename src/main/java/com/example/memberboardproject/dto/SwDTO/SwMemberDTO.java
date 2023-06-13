package com.example.memberboardproject.dto.SwDTO;

import com.example.memberboardproject.entity.SwEntity.SwMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
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

    public static SwMemberDTO toDTO(SwMemberEntity swMemberEntity) {
        SwMemberDTO swMemberDTO = new SwMemberDTO();
        swMemberDTO.setMemberEmail(swMemberEntity.getMemberEmail());
        swMemberDTO.setMemberPassword(swMemberEntity.getMemberPassword());
        swMemberDTO.setMemberName(swMemberEntity.getMemberName());
        swMemberDTO.setMemberMobile(swMemberEntity.getMemberMobile());
        swMemberDTO.setMemberBirth(swMemberEntity.getMemberBirth());
        swMemberDTO.setFileAttached(swMemberEntity.getFileAttached());
        return swMemberDTO;
    }

}