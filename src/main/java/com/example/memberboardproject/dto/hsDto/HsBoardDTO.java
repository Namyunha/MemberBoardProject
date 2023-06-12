package com.example.memberboardproject.dto.hsDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class HsBoardDTO {
    Long id;
    Long memberId;
    String boardTitle;
    String boardContents;
    int boardHits = 0;
    int fileAttached = 0;
    List<MultipartFile> boardFile;

}
