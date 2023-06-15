package com.example.memberboardproject.entity.jyEntity;

import com.example.memberboardproject.dto.jyDto.JyCommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jy_comment_table")
@Getter
@Setter
public class JyCommentEntity extends JyBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 200, nullable = false)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private JyMemberEntity jyMemberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private JyBoardEntity jyBoardEntity;

    public static JyCommentEntity toSaveEntity(JyBoardEntity jyBoardEntity, JyCommentDTO jyCommentDTO) {
        JyCommentEntity jyCommentEntity = new JyCommentEntity();
        jyCommentEntity.setCommentWriter(jyCommentDTO.getCommentWriter());
        jyCommentEntity.setCommentContents(jyCommentDTO.getCommentContents());
        jyCommentEntity.setJyBoardEntity(jyBoardEntity);
        return jyCommentEntity;
    }
}
