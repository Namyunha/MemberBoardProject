package com.example.memberboardproject.dto.kmdto;

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
    private int memberProfile;
    private List<MultipartFile> memberProfileFile;
    private List<String> profileOriginalFileName;
    private List<String> profileStoredFileName;


}
