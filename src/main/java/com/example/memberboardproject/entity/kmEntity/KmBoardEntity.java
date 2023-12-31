package com.example.memberboardproject.entity.kmEntity;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.util.kmUtil.KmUtilClass;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "km_board_table")
public class KmBoardEntity extends KmBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 50, nullable = false)
    public String boardTitle;
    @Column(length = 30, nullable = false)
    public String boardWriter;
    @Column(length = 1000, nullable = false)
    public String boardContents;
    @Column()
    public int boardHits;
    @Column()
    public int boardFileAttached;
    @OneToMany(mappedBy = "kmBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<KmBoardFileEntity> kmBoardFileEntityList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kmMemberId")
    private KmMemberEntity kmMemberEntity;

    public static KmBoardEntity saveToBoardEntity(KmBoardDTO kmBoardDTO, KmMemberEntity me){
        KmBoardEntity kmBoardEntity = new KmBoardEntity();
        kmBoardEntity.setKmMemberEntity(me);
        kmBoardEntity.setBoardTitle(kmBoardDTO.getBoardTitle());
        kmBoardEntity.setBoardWriter(kmBoardDTO.getBoardWriter());
        kmBoardEntity.setBoardContents(kmBoardDTO.getBoardContents());
        kmBoardEntity.setBoardHits(0);
        kmBoardEntity.setBoardFileAttached(0);
        return kmBoardEntity;
    }

    public static KmBoardEntity saveToBoardEntityWithFile(KmBoardDTO kmBoardDTO, KmMemberEntity me) {
        KmBoardEntity kmBoardEntity = saveToBoardEntity(kmBoardDTO, me);
        kmBoardEntity.setBoardFileAttached(1);
        return kmBoardEntity;

    }

    public static KmBoardEntity updateToBoardEntity(KmBoardDTO kmBoardDTO) {
        KmBoardEntity kmBoardEntity = new KmBoardEntity();
        kmBoardEntity.setId(kmBoardDTO.getId());
        kmBoardEntity.setBoardTitle(kmBoardDTO.getBoardTitle());
        kmBoardEntity.setBoardWriter(kmBoardDTO.getBoardWriter());
        kmBoardEntity.setBoardContents(kmBoardDTO.getBoardContents());
        kmBoardEntity.setBoardFileAttached(kmBoardDTO.getBoardFileAttached());
        kmBoardEntity.setBoardHits(kmBoardDTO.getBoardHits()+1);
        return kmBoardEntity;
    }
}
