package com.example.memberboardproject.entity.yhEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "yh_member_file_table")
@Getter
@Setter
public class YhMemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String originalFileName;

    @Column(length = 50, nullable = false)
    private String storedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yh_member_id")
    private YhMemberEntity yhMemberEntity;

    public static YhMemberFileEntity toSaveFileEntity(YhMemberEntity savedFile, String originalFileName, String storedFileName) {
        YhMemberFileEntity yhMemberFileEntity = new YhMemberFileEntity();
        yhMemberFileEntity.setOriginalFileName(originalFileName);
        yhMemberFileEntity.setStoredFileName(storedFileName);
        yhMemberFileEntity.setYhMemberEntity(savedFile);
        return yhMemberFileEntity;
    }
}
