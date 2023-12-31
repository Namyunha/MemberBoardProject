package com.example.memberboardproject.entity.kmEntity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class KmBaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime kmCreatedAt;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime kmUpdatedAt;
}
