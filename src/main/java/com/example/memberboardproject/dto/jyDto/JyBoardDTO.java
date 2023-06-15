package com.example.memberboardproject.dto.jyDto;

import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import com.example.memberboardproject.entity.jyEntity.JyBoardFileEntity;
import com.example.memberboardproject.entity.kmEntity.KmMemberEntity;
import com.example.memberboardproject.util.jyUtil.JyUtilClass;
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

    public static JyBoardDTO toDTO(JyBoardEntity jyBoardEntity) {
        JyBoardDTO jyBoardDTO = new JyBoardDTO();
        jyBoardDTO.setId(jyBoardEntity.getId());
        jyBoardDTO.setBoardTitle(jyBoardEntity.getBoardTitle());
        jyBoardDTO.setBoardWriter(jyBoardEntity.getBoardWriter());
        jyBoardDTO.setBoardContents(jyBoardEntity.getBoardContents());
        jyBoardDTO.setBoardHits(jyBoardEntity.getBoardHits());
        jyBoardDTO.setCreatedAt(JyUtilClass.dateFormat(jyBoardEntity.getCreatedAt()));

        // 파일 여부
        if (jyBoardEntity.getFileAttached() == 1) {
            // 파일 있음
            jyBoardDTO.setFileAttached(1);
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for (JyBoardFileEntity jyBoardFileEntity : jyBoardEntity.getJyBoardFileEntityList()) {
                originalFileNameList.add(jyBoardFileEntity.getOriginalFileName());
                storedFileNameList.add(jyBoardFileEntity.getStoredFileName());
            }
            jyBoardDTO.setOriginalFileName(originalFileNameList);
            jyBoardDTO.setStoredFileName(storedFileNameList);
        } else {
            // 파일 없음
            jyBoardDTO.setFileAttached(0);
        }
        return jyBoardDTO;
    }
}
