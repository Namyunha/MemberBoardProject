package com.example.memberboardproject.dto.jyDto;

import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JyBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private String createdAt;

    private List<MultipartFile> boardFile;
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

}
