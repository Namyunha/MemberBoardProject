package com.example.memberboardproject.dto.SwDTO;

import com.example.memberboardproject.entity.SwEntity.SwBoardEntity;
import com.example.memberboardproject.entity.SwEntity.SwBoardFileEntity;
import com.example.memberboardproject.util.SwUtil.SwUtilClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits = 0;
    private String createdAt;
    private List<MultipartFile> swBoardFile;
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static SwBoardDTO toDTO(SwBoardEntity swBoardEntity) {
        SwBoardDTO swBoardDTO = new SwBoardDTO();
        swBoardDTO.setId(swBoardEntity.getId());
        swBoardDTO.setBoardWriter(swBoardEntity.getBoardWriter());
        swBoardDTO.setBoardTitle(swBoardEntity.getBoardTitle());
        swBoardDTO.setBoardHits(swBoardEntity.getBoardHits());
        swBoardDTO.setBoardContents(swBoardEntity.getBoardContents());
        swBoardDTO.setCreatedAt(SwUtilClass.dateFormat(swBoardEntity.getCreatedAt()));

        if(swBoardEntity.getFileAttached() == 1) {
            swBoardDTO.setFileAttached(1);
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for(SwBoardFileEntity swBoardFileEntity : swBoardEntity.getSwBoardFileEntityList()) {
                originalFileNameList.add(swBoardFileEntity.getOriginalFileName());
                storedFileNameList.add(swBoardFileEntity.getStoredFileName());
            }
            swBoardDTO.setOriginalFileName(originalFileNameList);
            swBoardDTO.setStoredFileName(storedFileNameList);
        }else {
            swBoardDTO.setFileAttached(0);
        }
        return swBoardDTO;


    }

}
