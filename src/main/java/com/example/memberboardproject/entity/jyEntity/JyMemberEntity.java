package com.example.memberboardproject.entity.jyEntity;

import com.example.memberboardproject.dto.jyDto.JyMemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "jy_member_table")
public class JyMemberEntity extends JyBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberPassword;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 30)
    private String memberMobile;

    @Column(length = 20)
    private String memberBirth;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "jyMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JyMemberFileEntity> jyMemberFileEntityList = new ArrayList<>();

    public static JyMemberEntity toSaveEntity(JyMemberDTO jyMemberDTO) {
        JyMemberEntity jyMemberEntity = new JyMemberEntity();
        jyMemberEntity.setMemberEmail(jyMemberDTO.getMemberEmail());
        jyMemberEntity.setMemberPassword(jyMemberDTO.getMemberPassword());
        jyMemberEntity.setMemberName(jyMemberDTO.getMemberName());
        jyMemberEntity.setMemberMobile(jyMemberDTO.getMemberMobile());
        jyMemberEntity.setMemberBirth(jyMemberDTO.getMemberBirth());
        jyMemberEntity.setFileAttached(0);
        return jyMemberEntity;
    }

    public static JyMemberEntity toSaveEntityWithFile(JyMemberDTO jyMemberDTO) {
        JyMemberEntity jyMemberEntity = new JyMemberEntity();
        jyMemberEntity.setMemberEmail(jyMemberDTO.getMemberEmail());
        jyMemberEntity.setMemberPassword(jyMemberDTO.getMemberPassword());
        jyMemberEntity.setMemberName(jyMemberDTO.getMemberName());
        jyMemberEntity.setMemberMobile(jyMemberDTO.getMemberMobile());
        jyMemberEntity.setMemberBirth(jyMemberDTO.getMemberBirth());
        jyMemberEntity.setFileAttached(1);
        return jyMemberEntity;
    }
}
