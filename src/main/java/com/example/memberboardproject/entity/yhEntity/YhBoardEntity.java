package com.example.memberboardproject.entity.yhEntity;

import com.example.memberboardproject.dto.yhdDto.YhBoardDTO;
import com.example.memberboardproject.util.yhUtil.YhUtilClass;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "yh_board_table")
public class YhBoardEntity extends YhUtilClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardTitle;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 500, nullable = false)
    private String boardContents;

    @Column
    private int BoardHits;

    @Column
    private String createdAt;

    @Column
    private int fileAttached;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yh_member_id")
    private YhMemberEntity yhMemberEntity;

    @OneToMany(mappedBy = "yhBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    List<YhBoardFileEntity> yhBoardFileEntityList = new ArrayList<>();

    public static YhBoardEntity toSaveEntity(YhBoardDTO yhBoardDTO) {
        YhBoardEntity yhBoardEntity = new YhBoardEntity();
        yhBoardEntity.setBoardTitle(yhBoardDTO.getBoardTitle());
        yhBoardEntity.setBoardWriter(yhBoardDTO.getBoardWriter());
        yhBoardEntity.setBoardContents(yhBoardDTO.getBoardContents());
        yhBoardEntity.setBoardHits(0);
        yhBoardEntity.setFileAttached(0);
        return yhBoardEntity;
    }

    public static YhBoardEntity toSaveEntityWithFile(YhBoardDTO yhBoardDTO) {
        YhBoardEntity yhBoardEntity = new YhBoardEntity();
        yhBoardEntity.setBoardTitle(yhBoardDTO.getBoardTitle());
        yhBoardEntity.setBoardWriter(yhBoardDTO.getBoardWriter());
        yhBoardEntity.setBoardContents(yhBoardDTO.getBoardContents());
        yhBoardEntity.setBoardHits(0);
        yhBoardEntity.setFileAttached(1);
        return yhBoardEntity;
    }
}
