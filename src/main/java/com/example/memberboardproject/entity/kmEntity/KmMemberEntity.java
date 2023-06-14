package com.example.memberboardproject.entity.kmEntity;

import com.example.memberboardproject.dto.kmdto.KmMemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "kmMember_table")
public class KmMemberEntity extends KmBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 50, nullable = false)
    public String memberEmail;
    @Column(length = 20, nullable = false)
    public String memberPass;
    @Column(length = 30, nullable = false)
    public String memberMobile;
    @Column(length = 30, nullable = false)
    public String memberName;
    @Column(length = 30, nullable = false)
    public String memberBirth;

    @Column()
    public int memberProfile;

    @OneToMany(mappedBy = "kmMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<KmMemberFileEntity> kmMemberFileEntityList = new ArrayList<>();

    public static KmMemberEntity saveToKmMemberEntity(KmMemberDTO kmMemberDTO) {
        KmMemberEntity kmMemberEntity = new KmMemberEntity();
        kmMemberEntity.setMemberEmail(kmMemberDTO.getMemberEmail());
        kmMemberEntity.setMemberPass(kmMemberDTO.getMemberPass());
        kmMemberEntity.setMemberName(kmMemberDTO.getMemberName());
        kmMemberEntity.setMemberMobile(kmMemberDTO.getMemberMobile());
        kmMemberEntity.setMemberBirth(kmMemberDTO.getMemberBirth());
        kmMemberEntity.setMemberProfile(0);
        return kmMemberEntity;

    }

    public static KmMemberEntity saveToKmMemberEntityWithFile(KmMemberDTO kmMemberDTO) {
        KmMemberEntity kmMemberEntity = saveToKmMemberEntity(kmMemberDTO);
        kmMemberEntity.setMemberProfile(1);
        return kmMemberEntity;

    }


}
