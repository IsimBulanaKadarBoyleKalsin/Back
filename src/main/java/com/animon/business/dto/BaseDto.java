//package com.animon.business.dto;
//
//import com.animon.audit.AuditingAwareBaseDto;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.io.Serializable;
//import java.util.Date;
//
//// LOMBOK
//@Getter
//@Setter
//@ToString
//@Builder
//// BaseDTO
//abstract public class BaseDto extends AuditingAwareBaseDto implements Serializable {
//
//    // Serileştirme
//    public static final Long serialVersionUID=1L;
//
//    // ID
//    protected Long id;
//
//    @Builder.Default
//    protected Date systemCreatedDate=new Date(System.currentTimeMillis());
//
//}