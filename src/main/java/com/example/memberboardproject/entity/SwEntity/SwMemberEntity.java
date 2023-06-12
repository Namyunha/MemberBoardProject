package com.example.memberboardproject.entity.SwEntity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Swmember_table")
public class SwMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String memberEmail;

    @Column(length = 30, nullable = false)
    private String memberPassword;

    @Column(length = 10, nullable = false)
    private String memberName;

    @Column(length = 20, nullable = false)
    private String memberMobile;

    @Column(length = 20, nullable = false)
    private String memberBirth;

    @Column
    private int fileAttached;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "swMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SwMemberFileEntity> swMemberFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "swMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SwBoardEntity> swBoardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "swMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SwCommentEntity> swCommentEntityList = new ArrayList<>();
}
