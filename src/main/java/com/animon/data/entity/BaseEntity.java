//package com.animon.data.entity;
//
//import com.animon.audit.AuditingAwareBaseEntity;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Getter
//@Setter
//@ToString
//@MappedSuperclass
//@JsonIgnoreProperties(value = {},allowGetters = true) // Json, burada verdiğim özellikleri takip etme
//@Builder
//abstract public class BaseEntity extends AuditingAwareBaseEntity implements Serializable {
//
//    public static final Long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    protected Long id;
//
//    @Builder.Default
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "system_created_date")
//    protected Date systemCreatedDate=new Date(System.currentTimeMillis());
//}
