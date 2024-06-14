package com.animon.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleApi <D>{

    public ResponseEntity<?> roleApiCreate(D d);

    public ResponseEntity<List<D>> roleApiList();

    public ResponseEntity<?> roleApiFindById(Long id);

    public ResponseEntity<?> roleApiUpdateById(Long id, D d);

    public ResponseEntity<?> roleApiDeleteById(Long id);
}
