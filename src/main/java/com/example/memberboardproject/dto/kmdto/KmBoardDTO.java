package com.example.memberboardproject.dto.kmdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KmBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private String boardCreatedAt;
    private int boardFileAttached;
    private int boardHits;
    private List<String> originalBoardFileName;
    private List<String> storedBoardFileName;
    private List<MultipartFile> boardFile;


}