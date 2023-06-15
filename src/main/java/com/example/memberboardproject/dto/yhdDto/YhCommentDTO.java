package com.example.memberboardproject.dto.yhdDto;


import com.example.memberboardproject.entity.yhEntity.YhCommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class YhCommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;

    public static YhCommentDTO toDTO(YhCommentEntity comment) {
        YhCommentDTO yhCommentDTO = new YhCommentDTO();
        yhCommentDTO.setCommentWriter(comment.getCommentWriter());
        yhCommentDTO.setCommentContents(comment.getCommentContents());
        yhCommentDTO.setBoardId(comment.getYhBoardEntity().getId());
        return yhCommentDTO;
    }
}
