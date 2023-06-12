package com.example.memberboardproject.dto.hsDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class HsMemberDTO {
    Long id;
    String memberEmail;
    String memberPassword;
    String memberName;
    String memberMobile;
    String memberBirth;
    int fileAttached = 0;
    List<MultipartFile> memberFile;

}
