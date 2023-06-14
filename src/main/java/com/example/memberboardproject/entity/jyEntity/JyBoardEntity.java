package com.example.memberboardproject.entity.jyEntity;

import com.example.memberboardproject.dto.jyDto.JyBoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jy_board_table")
@Getter
@Setter
public class JyBoardEntity extends JyBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String boardTitle;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "jyBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JyBoardFileEntity> jyBoardFileEntityList = new ArrayList<>();

    public static JyBoardEntity toSaveEntity(JyBoardDTO jyBoardDTO) {
        JyBoardEntity jyBoardEntity = new JyBoardEntity();
        jyBoardEntity.setBoardTitle(jyBoardDTO.getBoardTitle());
        jyBoardEntity.setBoardWriter(jyBoardDTO.getBoardWriter());
        jyBoardEntity.setBoardContents(jyBoardDTO.getBoardContents());
        jyBoardEntity.setBoardHits(0);
        jyBoardEntity.setFileAttached(0);
        return jyBoardEntity;
    }

    public static JyBoardEntity toSaveEntityWithFile(JyBoardDTO jyBoardDTO) {
        JyBoardEntity jyBoardEntity = new JyBoardEntity();
        jyBoardEntity.setBoardTitle(jyBoardDTO.getBoardTitle());
        jyBoardEntity.setBoardWriter(jyBoardDTO.getBoardWriter());
        jyBoardEntity.setBoardContents(jyBoardDTO.getBoardContents());
        jyBoardEntity.setBoardHits(0);
        jyBoardEntity.setFileAttached(1);
        return jyBoardEntity;
    }
}
