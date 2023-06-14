package com.example.memberboardproject.entity.kmEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kmMember_File_table")
public class KmMemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 300,nullable = false)
    public String profileOriginalFileName;
    @Column(length = 500,nullable = false)
    public String profileStoredFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="kmMember_id")
    private KmMemberEntity kmMemberEntity;

    public static KmMemberFileEntity toSaveKmMemberFileEntity(KmMemberEntity kmSavedEntity,
                                                              String profileOriginalFileName,
                                                              String profileStoredFileName) {
        KmMemberFileEntity kmMemberFileEntity = new KmMemberFileEntity();
        kmMemberFileEntity.setKmMemberEntity(kmSavedEntity);
        kmMemberFileEntity.setProfileOriginalFileName(profileOriginalFileName);
        kmMemberFileEntity.setProfileStoredFileName(profileStoredFileName);
        return kmMemberFileEntity;
    }
}
