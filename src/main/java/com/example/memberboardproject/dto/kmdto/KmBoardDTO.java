package com.example.memberboardproject.dto.kmdto;

import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.entity.kmEntity.KmBoardFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.example.memberboardproject.util.kmUtil.KmUtilClass.kmDateFormat;

@Data
@Builder
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

    public static KmBoardDTO toDTO(KmBoardEntity kb) {
        KmBoardDTO kmBoardDTO = new KmBoardDTO();
        kmBoardDTO.setId(kb.getId());
        kmBoardDTO.setBoardTitle(kb.getBoardTitle());
        kmBoardDTO.setBoardWriter(kb.getBoardWriter());
        kmBoardDTO.setBoardContents(kb.getBoardContents());
        kmBoardDTO.setBoardHits(kb.getBoardHits());
        kmBoardDTO.setBoardCreatedAt(kmDateFormat(kb.getKmCreatedAt()));
        //파일 있을 시 처리
        if(kb.getBoardFileAttached()==1){
            kmBoardDTO.setBoardFileAttached(1);
            List<String> orginalBoardFileNameList = new ArrayList<>();
            List<String> storedBoardFileNameList = new ArrayList<>();
            for(KmBoardFileEntity kmBoardFileEntity : kb.getKmBoardFileEntityList()){
                orginalBoardFileNameList.add(kmBoardFileEntity.getOriginalBoardFileName());
                storedBoardFileNameList.add(kmBoardFileEntity.getStoredBoardFileName());
            }
            kmBoardDTO.setOriginalBoardFileName(orginalBoardFileNameList);
            kmBoardDTO.setStoredBoardFileName(storedBoardFileNameList);

        }else {
            kmBoardDTO.setBoardFileAttached(0);
        }
        return kmBoardDTO;
    }


}