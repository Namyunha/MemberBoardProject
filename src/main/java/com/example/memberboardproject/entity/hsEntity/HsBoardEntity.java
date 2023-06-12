package com.example.memberboardproject.entity.hsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hsboard_table")
public class HsBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String boardTitle;

    @Column(nullable = false)
    String boardContents;

    @Column
    int boardHits;

    @Column
    int fileAttached;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    HsMemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<HsFileEntity> hsFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<HsCommentEntity> hsCommentEntityList = new ArrayList<>();
}
