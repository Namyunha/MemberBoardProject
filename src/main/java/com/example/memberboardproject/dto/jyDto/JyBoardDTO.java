package com.example.memberboardproject.dto.jyDto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JyBoardDTO {
    private Long id;
    private String boardTile;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private String createdAt;

    private List<MultipartFile> boardFile;
    private int fileAttached;
    private List<String> originalFileName;
    private List<String> storedFileName;
}
