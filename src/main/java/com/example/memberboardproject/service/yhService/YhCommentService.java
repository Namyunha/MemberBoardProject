package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.dto.yhdDto.YhCommentDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhCommentEntity;
import com.example.memberboardproject.repository.yhRepository.YhBoardRepository;
import com.example.memberboardproject.repository.yhRepository.YhCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class YhCommentService {
    private final YhCommentRepository yhCommentRepository;
    private final YhBoardRepository yhBoardRepository;

    public Long save(YhCommentDTO yhCommentDTO) {
        YhBoardEntity boardEntity = yhBoardRepository.findById(yhCommentDTO.getBoardId()).orElseThrow();
        YhCommentEntity yhCommentEntity = YhCommentEntity.toSaveEntity(boardEntity, yhCommentDTO);
        yhCommentRepository.save(yhCommentEntity);
        return yhCommentEntity.getId();
    }

    @Transactional
    public List<YhCommentDTO> findAll(Long boardId) {
        List<YhCommentDTO> yhCommentDTOList = new ArrayList<>();
        YhBoardEntity boardEntity = yhBoardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        List<YhCommentEntity> commentEntityList = boardEntity.getYhCommentEntityList();
        commentEntityList.forEach(comment -> {
            yhCommentDTOList.add(YhCommentDTO.toDTO(comment));
        });
        return yhCommentDTOList;
    }

    public Page<YhCommentDTO> findCommentPaging(Pageable pageable) {
        int limit = 5;
        int page = pageable.getPageNumber();
        Page<YhCommentEntity> yhCommentEntities = yhCommentRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id")));
        Page<YhCommentDTO> yhCommentDTO = yhCommentEntities.map(yhCommentEntity ->
                YhCommentDTO.builder()
                        .id(yhCommentEntity.getId())
                        .commentWriter(yhCommentEntity.getCommentWriter())
                        .commentContents(yhCommentEntity.getCommentContents())
                        .boardId(yhCommentEntity.getYhBoardEntity().getId())
                        .build());
        return yhCommentDTO;
    }

//    @Transactional
//    public List<CommentDTO> findAll(Long boardId) {
//        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
//        // 1. BoardEntity에서 댓글 목록 가져오기
////        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
//        // 2. CommentRepository에서 가져오기
//        // select * from comment_table where board_id=?
//        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);
//
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        commentEntityList.forEach(comment -> {
//            commentDTOList.add(CommentDTO.toDTO(comment));
//        });
//        return commentDTOList;
//    }
}
