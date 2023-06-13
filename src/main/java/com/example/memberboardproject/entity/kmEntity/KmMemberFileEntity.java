package com.example.memberboardproject.entity.kmEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kmMemberFileEntity")
public class KmMemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 300,nullable = false)
    public String profileOriginalFileName;
    @Column(length = 500,nullable = false)
    public String profileStoredFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="kmMember_id")
    private KmMemberEntity kmMemberEntity;


}
