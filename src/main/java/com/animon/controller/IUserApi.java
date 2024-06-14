package com.animon.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserApi <D>{



    public ResponseEntity<?> userApiCreate(Long rolesId,D d);

    public ResponseEntity<List<D>> userApiList();

    public ResponseEntity<?> userApiFindById(Long id);

    public ResponseEntity<?> userApiUpdateById(Long id, D d);

    public ResponseEntity<?> userApiDeleteById(Long id);
}
