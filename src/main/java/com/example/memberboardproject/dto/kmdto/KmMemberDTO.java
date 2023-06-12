package com.example.memberboardproject.dto.kmdto;

import lombok.Data;

@Data
public class KmMemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPass;
    private String memberName;
    private String memberMobile;
    private int memberProfile;


}
