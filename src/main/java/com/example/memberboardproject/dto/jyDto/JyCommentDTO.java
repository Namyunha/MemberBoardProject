package com.example.memberboardproject.dto.jyDto;

import com.example.memberboardproject.entity.jyEntity.JyCommentEntity;
import com.example.memberboardproject.util.jyUtil.JyUtilClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JyCommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private String createdAt;
    private Long boardId;

    public static JyCommentDTO toDTO(JyCommentEntity jyCommentEntity) {
        JyCommentDTO jyCommentDTO = new JyCommentDTO();
        jyCommentDTO.setId(jyCommentEntity.getId());
        jyCommentDTO.setCommentWriter(jyCommentEntity.getCommentWriter());
        jyCommentDTO.setCommentContents(jyCommentEntity.getCommentContents());
        jyCommentDTO.setCreatedAt(JyUtilClass.dateFormat(jyCommentEntity.getCreatedAt()));
        jyCommentDTO.setBoardId(jyCommentEntity.getJyBoardEntity().getId());
        return jyCommentDTO;
    }
}
