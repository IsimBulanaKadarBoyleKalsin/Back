package com.animon.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public class AuditingAwareBaseEntity implements Serializable {

    public static final Long serialVersionUUID = 1L;

    // Kim ekledi
    @CreatedBy
    @Column(name="created_user",updatable = false)
    protected String createdBy;

    // Kim ne zaman ekledi
    @CreatedDate
    @Column(name="created_date",updatable = false)
    protected Date createdDate;

    // Kim güncelledi
    @LastModifiedBy
    @Column(name="last_user")
    protected String lastModifiedBy;

    // Kim ne zaman güncelledi
    @LastModifiedDate
    @Column(name="last_date")
    protected Date lastModifiedDate;
}
