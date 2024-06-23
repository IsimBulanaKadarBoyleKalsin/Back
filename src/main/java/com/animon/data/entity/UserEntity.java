package com.animon.data.entity;

import com.animon.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Table(name = "users" )
@Entity(name = "Users")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class UserEntity extends AuditingAwareBaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    // System Created Date
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;


    @Column(name = "user_nickname",length = 20,nullable = false,unique = true)
    private String userNickName;

    @Column(name = "user_name",length = 20,nullable = false)
    private String userName;

    @Column(name = "user_surname",length = 20,nullable = false)
    private String userSurname;

    @Column(name = "user_password")
    private String password;

    @Column(
            name = "user_email",
            nullable = false,
            updatable = false,
            insertable = true,
            length = 60,
            columnDefinition = "varchar(255) default 'heehee@gmail.com'")
    private String userEmail;

    @Column(name = "user_neighborhood")
    private String neighborHood;

    @Column(name = "page_authorization")
    private Boolean pageAuthorization = false;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roleEntities = new HashSet<>();

}
