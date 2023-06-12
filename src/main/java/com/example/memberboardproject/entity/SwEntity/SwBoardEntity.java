package com.example.memberboardproject.entity.SwEntity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Swboard_table")
@Getter
@Setter
public class SwBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String boardTitle;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 500, nullable = false)
    private String boardContents;

    @Column
    private int boardHits;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private SwMemberEntity swMemberEntity;

    @OneToMany(mappedBy = "swBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SwBoardFileEntity> swBoardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "swBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SwCommentEntity> swCommentEntityList = new ArrayList<>();




}