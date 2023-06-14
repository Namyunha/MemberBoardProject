package com.example.memberboardproject.entity.yhEntity;


import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "yh_member_table")
@Getter
@Setter
public class YhMemberEntity extends YhBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 20, nullable = false)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberPassword;

    @Column(length = 20, nullable = false)
    private String memberMobile;

    @Column(length = 10, nullable = false)
    private String memberBirth;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "yhMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<YhMemberFileEntity> yhMemberFileEntityList = new ArrayList<>();


    public static YhMemberEntity toSaveEntity(YhMemberDTO yhMemberDTO) {
        YhMemberEntity yhMemberEntity = new YhMemberEntity();
        yhMemberEntity.setMemberName(yhMemberDTO.getMemberName());
        yhMemberEntity.setMemberEmail(yhMemberDTO.getMemberEmail());
        yhMemberEntity.setMemberPassword(yhMemberDTO.getMemberPassword());
        yhMemberEntity.setMemberMobile(yhMemberDTO.getMemberMobile());
        yhMemberEntity.setMemberBirth(yhMemberDTO.getMemberBirth());
        yhMemberEntity.setFileAttached(0);
        return yhMemberEntity;
    }


    public static YhMemberEntity toSaveEntityWithFile(YhMemberDTO yhMemberDTO) {
        YhMemberEntity yhMemberEntity = new YhMemberEntity();
        yhMemberEntity.setMemberName(yhMemberDTO.getMemberName());
        yhMemberEntity.setMemberEmail(yhMemberDTO.getMemberEmail());
        yhMemberEntity.setMemberPassword(yhMemberDTO.getMemberPassword());
        yhMemberEntity.setMemberMobile(yhMemberDTO.getMemberMobile());
        yhMemberEntity.setMemberBirth(yhMemberDTO.getMemberBirth());
        yhMemberEntity.setFileAttached(1);
        return yhMemberEntity;
    }
}
