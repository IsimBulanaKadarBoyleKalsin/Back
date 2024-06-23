package com.animon.audit;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;

    // AUDITING
    // Kim ekledi
    // @JsonIgnore // Backte giden veride bunu göstermemek için kullanılır.
    protected String createdBy;

    // Kim ne zaman ekledi
    // @JsonIgnore // Backte giden veride bunu göstermemek için kullanılır.
    protected Date createdDate;

    // Kim güncelledi
    // @JsonIgnore // Backte giden veride bunu göstermemek için kullanılır.
    protected String lastModifiedBy;

    // Kim ne zaman güncelledi
    // @JsonIgnore // Backte giden veride bunu göstermemek için kullanılır.
    protected Date lastModifiedDate;
}
