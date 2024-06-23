package com.animon.data.entity;

import com.animon.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "Roles")
@Table(name = "roles")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleEntity extends AuditingAwareBaseEntity implements Serializable {

    private static final Long serialVersionUUID = 1L;

    // Role ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    // Role Name
    // Validation = columnDefinition =>  Default USER olsun
    @Column(name = "role_name", length = 20,nullable = false, columnDefinition = "varchar(255) default USER")
    private String roleName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;


}
