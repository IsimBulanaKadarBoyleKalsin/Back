package com.animon.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Auditing
@JsonIgnoreProperties(value={"created_date","last_date"},allowGetters = true) // json buradaki verileri takip etme
public class AuditingAwareBaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    // AUDITING
    // Kim ekledi
    @JsonIgnore // Backte giden veride bunu göstermek
    @CreatedBy
    @Column(name="created_user")
    protected String createdUser;

    // Kim ne zaman ekledi
    @JsonIgnore // Backte giden veride bunu göstermek
    @CreatedDate
    @Column(name="created_date")
    protected Date createdDate;

    // Kim güncelledi
    @JsonIgnore // Backte giden veride bunu göstermek
    @LastModifiedBy
    @Column(name="last_user")
    protected String lastUser;

    // Kim ne zaman güncelledi
    @JsonIgnore // Backte giden veride bunu göstermek
    @LastModifiedDate
    @Column(name="last_date")
    protected Date lastDate;


}
