package com.example.memberboardproject.entity.hsEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hsmember_table")
@Getter
@Setter
public class HsMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique = true)
    String memberEmail;

    @Column(nullable = false)
    String memberPassword;

    @Column(nullable = false)
    String memberName;

    @Column
    String memberMobile;

    @Column
    String memberBirth;

    @Column
    int fileAttached;

    @OneToMany(mappedBy = "hsMemberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<HsBoardEntity> hsBoardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "hsMemberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<HsCommentEntity> hsCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "hsMemberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    List<HsFileEntity> hsFileEntityList = new ArrayList<>();
}
