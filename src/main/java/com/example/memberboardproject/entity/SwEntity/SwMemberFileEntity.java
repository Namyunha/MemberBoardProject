package com.example.memberboardproject.entity.SwEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "Swmember_file_table")
public class SwMemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private SwMemberEntity swMemberEntity;

    public static SwMemberFileEntity toSaveMemberFileEntity(SwMemberEntity swMemberEntity,String originalFileName,String storedFileName) {
        SwMemberFileEntity swMemberFileEntity = new SwMemberFileEntity();
        swMemberFileEntity.setSwMemberEntity(swMemberEntity);
        swMemberFileEntity.setOriginalFileName(originalFileName);
        swMemberFileEntity.setStoredFileName(storedFileName);
        return swMemberFileEntity;
    }
    public static SwMemberFileEntity toUpdateMemberFileEntity(SwMemberEntity swMemberEntity,String originalFileName,String storedFileName) {
        SwMemberFileEntity swMemberFileEntity = new SwMemberFileEntity();
        swMemberFileEntity.setId(swMemberEntity.getId());
        swMemberFileEntity.setSwMemberEntity(swMemberEntity);
        swMemberFileEntity.setOriginalFileName(originalFileName);
        swMemberFileEntity.setStoredFileName(storedFileName);
        return swMemberFileEntity;
    }

}
