package com.example.memberboardproject.service.jyService;

import com.example.memberboardproject.dto.jyDto.JyCommentDTO;
import com.example.memberboardproject.entity.jyEntity.JyBoardEntity;
import com.example.memberboardproject.entity.jyEntity.JyCommentEntity;
import com.example.memberboardproject.repository.jyRepository.JyBoardRepository;
import com.example.memberboardproject.repository.jyRepository.JyCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JyCommentService {
    private final JyCommentRepository jyCommentRepository;
    private final JyBoardRepository jyBoardRepository;

    @Transactional
    public List<JyCommentDTO> findAll(Long boardId) {
        JyBoardEntity jyBoardEntity = jyBoardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        List<JyCommentEntity> jyCommentEntityList = jyCommentRepository.findByJyBoardEntityOrderByIdDesc(jyBoardEntity);

        List<JyCommentDTO> jyCommentDTOList = new ArrayList<>();
        jyCommentEntityList.forEach(comment -> {
            jyCommentDTOList.add(JyCommentDTO.toDTO(comment));
        });
        return jyCommentDTOList;
    }

    public Long save(JyCommentDTO jyCommentDTO) {
        JyBoardEntity jyBoardEntity = jyBoardRepository.findById(jyCommentDTO.getBoardId()).orElseThrow(() -> new  NoSuchElementException());
        JyCommentEntity jyCommentEntity = JyCommentEntity.toSaveEntity(jyBoardEntity, jyCommentDTO);
        return jyCommentRepository.save(jyCommentEntity).getId();
    }
}
