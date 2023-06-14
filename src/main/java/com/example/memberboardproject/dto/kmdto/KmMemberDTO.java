package com.example.memberboardproject.dto.kmdto;

import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberFileEntity;
import com.example.memberboardproject.util.kmUtil.KmUtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KmMemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPass;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private String memberCreatedAt;
    private int memberProfile;
    private List<MultipartFile> memberProfileFile;
    private List<String> profileOriginalFileName;
    private List<String> profileStoredFileName;

    public static KmMemberDTO toDTO(KmMemberEntity kmMemberEntity) {
        System.out.println("toDTO에넘어온kmMemberEntity = " + kmMemberEntity);
        
        KmMemberDTO kmMemberDTO = new KmMemberDTO();
        kmMemberDTO.setId(kmMemberEntity.getId());
        kmMemberDTO.setMemberEmail(kmMemberEntity.getMemberEmail());
        kmMemberDTO.setMemberPass(kmMemberEntity.getMemberPass());
        kmMemberDTO.setMemberName(kmMemberEntity.getMemberName());
        kmMemberDTO.setMemberMobile(kmMemberEntity.getMemberMobile());
//        kmMemberDTO.setMemberProfile(kmMemberEntity.getMemberProfile());
        kmMemberDTO.setMemberBirth(kmMemberEntity.getMemberBirth());
        kmMemberDTO.setMemberCreatedAt(KmUtilClass.kmDateFormat(kmMemberEntity.getKmCreatedAt()));

        System.out.println("엔티티프로필유무"+ kmMemberEntity.getMemberProfile());
        System.out.println("kmMemberDTO = " + kmMemberDTO);
        //프로필파일이 있을경우
        if(kmMemberEntity.getMemberProfile()==1){
            kmMemberDTO.setMemberProfile(1);
            List<String> profileOriginalFileNameList = new ArrayList<>();
            List<String> profileStoredFileNameList = new ArrayList<>();
            for (KmMemberFileEntity kmMemberFileEntity : kmMemberEntity.getKmMemberFileEntityList()){
                System.out.println("kmMemberFileEntity = " + kmMemberFileEntity);
                profileOriginalFileNameList.add(kmMemberFileEntity.getProfileOriginalFileName());
                profileStoredFileNameList.add(kmMemberFileEntity.getProfileStoredFileName());
            }
            kmMemberDTO.setProfileOriginalFileName(profileOriginalFileNameList);
            kmMemberDTO.setProfileStoredFileName(profileStoredFileNameList);

        }else {
            kmMemberDTO.setMemberProfile(0);
        }

        return kmMemberDTO;

    }
}
    //        if (kmMemberEntity.getMemberProfile() == 1) {
//            kmMemberDTO.setMemberProfile(1);
//            List<String> profileOriginalFileNameList = new ArrayList<>();
//            List<String> profileStoredFileNameList = new ArrayList<>();
//            for (KmMemberFileEntity kmMemberFileEntity : kmMemberEntity.getKmMemberFileEntityList()) {
//                profileOriginalFileNameList.add(kmMemberFileEntity.getProfileOriginalFileName());
//                profileStoredFileNameList.add(kmMemberFileEntity.getProfileStoredFileName());
//            }
//            kmMemberDTO.setProfileOriginalFileName(profileOriginalFileNameList);
//            kmMemberDTO.setProfileStoredFileName(profileStoredFileNameList);
//
//        }else{
//            kmMemberDTO.setMemberProfile(0);
//        }
