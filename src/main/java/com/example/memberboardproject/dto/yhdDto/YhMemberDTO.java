package com.example.memberboardproject.dto.yhdDto;


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
    private String memberMoblie;
    private String memberBirth;
    private String createdAt;
    private int fileAttached;
    private List<MultipartFile> memberProfile;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
}
