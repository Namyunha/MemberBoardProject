package com.example.memberboardproject.dto.yhdDto;

import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhBoardFileEntity;
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
    private String createdAt;
    private int boardHits;
    private int fileAttached;


    private List<MultipartFile> boardFile;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static YhBoardDTO toSaveDTO(YhBoardEntity boardEntity) {
        YhBoardDTO yhBoardDTO = new YhBoardDTO();
        yhBoardDTO.setId(boardEntity.getId());
        yhBoardDTO.setBoardTitle(boardEntity.getBoardTitle());
        yhBoardDTO.setBoardWriter(boardEntity.getBoardWriter());
        yhBoardDTO.setBoardContents(boardEntity.getBoardContents());
        yhBoardDTO.setBoardHits(boardEntity.getBoardHits());
        if (boardEntity.getFileAttached() == 1) {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for (YhBoardFileEntity boardFile : boardEntity.getYhBoardFileEntityList()) {
                String originalFileName = boardFile.getOriginalFileName();
                String storedFileName = boardFile.getStoredFileName();
                originalFileNameList.add(originalFileName);
                storedFileNameList.add(storedFileName);
            }
            yhBoardDTO.setFileAttached(1);
            yhBoardDTO.setOriginalFileName(originalFileNameList);
            yhBoardDTO.setStoredFileName(storedFileNameList);
        } else {
            yhBoardDTO.setFileAttached(0);
        }
        return yhBoardDTO;
    }

}
