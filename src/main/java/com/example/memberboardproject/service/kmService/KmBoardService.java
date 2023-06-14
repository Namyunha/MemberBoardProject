package com.example.memberboardproject.service.kmService;

import com.example.memberboardproject.dto.kmdto.KmBoardDTO;
import com.example.memberboardproject.entity.kmEntity.KmBoardEntity;
import com.example.memberboardproject.repository.kmRepository.KmBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KmBoardService {
    public final KmBoardRepository kmBoardRepository;
//    public final KmBoardFileRepository kmBoardFileRepository;

    public Long save(KmBoardDTO kmBoardDTO) {
        KmBoardEntity kmBoardEntity = KmBoardEntity.saveToBoardEntity(kmBoardDTO);
        return kmBoardRepository.save(kmBoardEntity).getId();


    }
}
