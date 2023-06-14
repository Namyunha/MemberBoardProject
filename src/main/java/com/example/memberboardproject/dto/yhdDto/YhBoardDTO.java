package com.example.memberboardproject.dto.yhdDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class YhBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private String boardHits;
    private String createdAt;
    private int fileAttached;


    private List<MultipartFile> boardFile;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
}
