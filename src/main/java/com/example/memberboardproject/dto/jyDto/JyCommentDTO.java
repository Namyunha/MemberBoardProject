package com.example.memberboardproject.dto.jyDto;

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
}
