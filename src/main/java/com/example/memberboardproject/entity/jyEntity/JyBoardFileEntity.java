package com.example.memberboardproject.entity.jyEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jy_board_file_table")
@Getter
@Setter
public class JyBoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private JyBoardEntity jyBoardEntity;

    public static JyBoardFileEntity toSaveBoardFileEntity(JyBoardEntity savedEntity, String originalFileName, String storedFileName) {
        JyBoardFileEntity jyBoardFileEntity = new JyBoardFileEntity();
        jyBoardFileEntity.setJyBoardEntity(savedEntity);
        jyBoardFileEntity.setOriginalFileName(originalFileName);
        jyBoardFileEntity.setStoredFileName(storedFileName);
        return jyBoardFileEntity;
    }
}
