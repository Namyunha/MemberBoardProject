package com.example.memberboardproject.entity.jyEntity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class JyBaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
