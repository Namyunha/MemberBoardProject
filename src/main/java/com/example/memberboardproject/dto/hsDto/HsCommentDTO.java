package com.example.memberboardproject.dto.hsDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HsCommentDTO {
    Long id;
    String commentWriter;
    String commentContents;
    Long memberId;
    Long boardId;
}
