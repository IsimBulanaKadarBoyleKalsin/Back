package com.animon.business.dto;

import com.animon.audit.AuditingAwareBaseDto;
import com.animon.role.ERole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class RoleDto extends AuditingAwareBaseDto implements Serializable {

    private static final Long serialVersionUUID = 1L;

    private Long roleId;

    @NotEmpty(message = "User role can not be null!")
    @Builder.Default
    private String roleName = ERole.REGULAR.toString();

    private Date systemCreatedDate;
}
