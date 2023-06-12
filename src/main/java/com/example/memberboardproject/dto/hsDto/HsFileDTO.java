package com.example.memberboardproject.dto.hsDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class HsFileDTO {
    Long id;
    String originalFileName;
    String storedFileName;
    Long boardId;
    Long memberId;
}
