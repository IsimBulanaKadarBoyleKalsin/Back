package com.animon.controller.api;

import com.animon.business.dto.UserDto;
import com.animon.business.service.IUserService;
import com.animon.controller.IUserApi;
import com.animon.error.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi implements IUserApi<UserDto> {

    private final IUserService userService;
    private ApiResult apiResult;



    @Override
    @PostMapping("/create/{roles_id}")
    public ResponseEntity<?> userApiCreate(
            @PathVariable(name = "roles_id",required = false) Long rolesId,
            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.userServiceCreate(rolesId,userDto));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> userApiList() {
        return ResponseEntity.ok(userService.userServiceList());
    }

    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> userApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.userServiceFindById(id));
    }

    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<?> userApiUpdateById(
            @PathVariable(name = "id",required = false) Long id,
            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.userServiceUpdateById(id,userDto));
    }

    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<?> userApiDeleteById(@PathVariable(name = "id",required = false) Long id) {
        return ResponseEntity.ok(userService.userServiceDeleteById(id));
    }
}
