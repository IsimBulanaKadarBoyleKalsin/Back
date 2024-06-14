package com.animon.controller.api;

import com.animon.business.dto.RoleDto;
import com.animon.business.service.IRoleService;
import com.animon.controller.IRoleApi;
import com.animon.error.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin
public class RoleApi implements IRoleApi<RoleDto> {

    private final IRoleService roleService;
    private ApiResult apiResult;


    @PostMapping("/create")
    @Override
    public ResponseEntity<?> roleApiCreate(@Valid @RequestBody RoleDto roleDto) {

        RoleDto roleCreateApi = (RoleDto) roleService.roleServiceCreate(roleDto);

        if (roleCreateApi == null) {
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(404)
                    .error("Role Eklenmedi")
                    .message("Role Dto bulunmadı")
                    .path("localhost:4444/role/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultCreate);
        } else if (roleCreateApi.getRoleId() == 0) {
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(400)
                    .error("Role Eklenmedi")
                    .message("Role Dto Bad Request")
                    .path("localhost:4444/role/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(400).body(apiResultCreate);
        }
        return ResponseEntity.status(201).body(roleService.roleServiceCreate(roleDto));
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<List<RoleDto>> roleApiList() {
        return ResponseEntity.ok(roleService.roleServiceList());
    }

    @GetMapping({"/find", "/find/{id}"})
    @Override
    public ResponseEntity<?> roleApiFindById(@PathVariable(name = "id", required = false) Long id) {
        RoleDto roleFindApi = (RoleDto) roleService.roleServiceFindById(id);

        if (roleFindApi == null) {
            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("Role Bulunamadı")
                    .message("Role Dto bulunmadı")
                    .path("localhost:4444/role/find")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }

        return ResponseEntity.ok(roleService.roleServiceFindById(id));

    }

    @PutMapping({"/update", "/update/{id}"})
    @Override
    public ResponseEntity<?> roleApiUpdateById(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody RoleDto roleDto) {


        return ResponseEntity.ok(roleService.roleServiceUpdateById(id, roleDto));


    }
    @DeleteMapping({"/delete","/delete/{id}"})
    @Override
    public ResponseEntity<?> roleApiDeleteById(@PathVariable(name = "id") Long id) {
        RoleDto roleDeleteApi = (RoleDto) roleService.roleServiceDeleteById(id);


        return ResponseEntity.ok(roleDeleteApi);
    }
}
