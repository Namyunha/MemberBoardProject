package com.example.memberboardproject.entity.kmEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "km_board_file_table")
public class KmBoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 300, nullable = false)
    public String storedBoardFileName;
    @Column(length = 500,nullable = false)
    public String originalBoardFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kmBoardId")
    private KmBoardEntity kmBoardEntity;


    public static KmBoardFileEntity saveToBoardFileEntity(KmBoardEntity kmBoardEntity, String originalBoardFileName, String storedBoardFileName) {
        KmBoardFileEntity kmBoardFileEntity = new KmBoardFileEntity();
        kmBoardFileEntity.setKmBoardEntity(kmBoardEntity);
        kmBoardFileEntity.setOriginalBoardFileName(originalBoardFileName);
        kmBoardFileEntity.setStoredBoardFileName(storedBoardFileName);
        return kmBoardFileEntity;
    }
}
