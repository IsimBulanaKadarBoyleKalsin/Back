package com.animon.role;

import lombok.Getter;

@Getter
public enum ERole {
    ADMIN(2L,"ADMIN"),
    VOLUNTEER(3L,"VOLUNTEER"),
    SUPPORTER(4L,"SUPPORTER"),
    REGULAR(5L,"REGULAR");



    private final Long key;
    private final String value;

    private ERole(Long key, String value){
        this.key = key;
        this.value = value;
    }

}
