package com.example.memberboardproject.entity.yhEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "yh_board_file_table")
public class YhBoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String originalFileName;

    @Column(length = 50, nullable = false)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yh_board_id")
    private YhBoardEntity yhBoardEntity;

    public static YhBoardFileEntity toSaveFileEntity(YhBoardEntity yhBoardEntity, String originalFileName, String storedFileName) {
        YhBoardFileEntity yhBoardFileEntity = new YhBoardFileEntity();
        yhBoardFileEntity.setOriginalFileName(originalFileName);
        yhBoardFileEntity.setStoredFileName(storedFileName);
        yhBoardFileEntity.setYhBoardEntity(yhBoardEntity);
        return yhBoardFileEntity;
    }


}
