package com.example.memberboardproject.entity.hsEntity;

import javax.persistence.*;

@Entity
@Table(name = "hscomment_table")
public class HsCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String commentWriter;

    @Column(nullable = false)
    String commentContents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    HsMemberEntity hsMemberEntity;

    @ManyToOne
    @JoinColumn(name = "board_id")
    HsBoardEntity hsBoardEntity;
}
