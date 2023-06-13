package com.example.memberboardproject.entity.hsEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hsboardFile_table")
@Getter
@Setter
public class HsFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String originalFileName;

    @Column
    String storedFileName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    HsBoardEntity hsBoardEntity;

    @ManyToOne
    @JoinColumn(name= "member_id")
    HsMemberEntity hsMemberEntity;

    public static HsFileEntity toSaveFileMemberEntity(HsMemberEntity savedEntity, String originalFileName, String storedFileName) {
        HsFileEntity hsFileEntity = new HsFileEntity();
        hsFileEntity.setHsMemberEntity(savedEntity);
        hsFileEntity.setOriginalFileName(originalFileName);
        hsFileEntity.setStoredFileName(storedFileName);
        return hsFileEntity;
    }
}
