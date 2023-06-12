package com.example.memberboardproject.dto.SwDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwMemberFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long member_id;
}
