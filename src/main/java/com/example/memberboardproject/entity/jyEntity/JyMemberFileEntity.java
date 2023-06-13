package com.example.memberboardproject.entity.jyEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "jy_member_file_table")
public class JyMemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private JyMemberEntity jyMemberEntity;

    public static JyMemberFileEntity toSaveMemberFileEntity(JyMemberEntity savedEntity, String originalFileName, String storedFileName) {
        JyMemberFileEntity jyMemberFileEntity = new JyMemberFileEntity();
        jyMemberFileEntity.setJyMemberEntity(savedEntity);
        jyMemberFileEntity.setOriginalFileName(originalFileName);
        jyMemberFileEntity.setStoredFileName(storedFileName);
        return jyMemberFileEntity;
    }
}
