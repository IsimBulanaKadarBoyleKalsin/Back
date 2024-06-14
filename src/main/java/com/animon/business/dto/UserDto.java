package com.animon.business.dto;

import com.animon.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID=1L;

    private Long userId;

    private Date systemCreatedDate;

    @NotEmpty(message = "User nickname cannot be empty!")
    private String userNickName;

    @NotEmpty(message = "User name cannot be empty!")
    private String userName;

    @NotEmpty(message = "User surname cannot be empty!")
    private String userSurname;

    @NotEmpty(message = "User email can not be empty!")
    @Email(message = "It must be in a email format")
    private String userEmail;

    @NotEmpty(message = "Password can not be empty!")
    @Size(min = 7,max=15,  message = "hee hee")

    private String userPassword;


    @Builder.Default
    private Boolean pageAuthorization=false;


    private Collection<RoleDto> roles ;
}
