package com.example.memberboardproject.dto.kmdto;

import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class KmMemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPass;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private int memberProfile;
    private List<MultipartFile> memberProfileFile;
    private List<String> profileOriginalFileName;
    private List<String> profileStoredFileName;

    public static KmMemberDTO toDTO(KmMemberEntity kmMemberEntity) {
        KmMemberDTO kmMemberDTO = new KmMemberDTO();
        kmMemberDTO.setId(kmMemberDTO.getId());
        kmMemberDTO.setMemberEmail(kmMemberEntity.getMemberEmail());
        kmMemberDTO.setMemberPass(kmMemberEntity.getMemberPass());
        kmMemberDTO.setMemberName(kmMemberEntity.getMemberName());
        kmMemberDTO.setMemberMobile(kmMemberEntity.getMemberMobile());
        kmMemberDTO.setMemberBirth(kmMemberDTO.getMemberBirth());
        return kmMemberDTO;


    }
}
