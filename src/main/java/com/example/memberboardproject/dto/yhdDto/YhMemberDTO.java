package com.example.memberboardproject.dto.yhdDto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
//    private List<MultipartFile> memberProfile;
//    private int fileAttached;
//    private List<String> originalFileName = new ArrayList<>();
//    private List<String> storedFileName = new ArrayList<>();
}
