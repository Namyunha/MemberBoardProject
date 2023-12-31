package com.example.memberboardproject.entity.SwEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "Swboard_file_table")
public class SwBoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private SwBoardEntity swBoardEntity;

    public static SwBoardFileEntity toSaveBoardFileEntity(SwBoardEntity swBoardEntity,String originalFileName,String storedFileName) {
        SwBoardFileEntity swBoardFileEntity = new SwBoardFileEntity();
        swBoardFileEntity.setSwBoardEntity(swBoardEntity);
        swBoardFileEntity.setOriginalFileName(originalFileName);
        swBoardFileEntity.setStoredFileName(storedFileName);
        return swBoardFileEntity;
    }

}
