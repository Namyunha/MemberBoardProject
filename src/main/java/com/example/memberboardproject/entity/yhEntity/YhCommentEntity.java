package com.example.memberboardproject.entity.yhEntity;


import com.example.memberboardproject.dto.yhdDto.YhCommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "yh_comment_table")
@Entity
@Getter
@Setter
public class YhCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yh_board_id")
    private YhBoardEntity yhBoardEntity;

    public static YhCommentEntity toSaveEntity(YhBoardEntity boardEntity, YhCommentDTO yhCommentDTO) {
        YhCommentEntity yhCommentEntity = new YhCommentEntity();
        yhCommentEntity.setCommentWriter(yhCommentDTO.getCommentWriter());
        yhCommentEntity.setCommentContents(yhCommentDTO.getCommentContents());
        yhCommentEntity.setYhBoardEntity(boardEntity);
        return yhCommentEntity;
    }
}
