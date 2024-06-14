package com.animon.business.service.impl;

import com.animon.business.dto.RoleDto;
import com.animon.business.service.IRoleService;
import com.animon.configuration.beanconfig.ModelMapperBean;
import com.animon.data.entity.RoleEntity;
import com.animon.data.repository.IRoleRepository;
import com.animon.exception.MyCustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService<RoleDto, RoleEntity> {

    private final IRoleRepository repository;
    private final ModelMapperBean modelMapperBean;

    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapperBean.modelMapperMethod().map(roleEntity, RoleDto.class);
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapperBean.modelMapperMethod().map(roleDto, RoleEntity.class);
    }

    @Override
    @Transactional
    @SneakyThrows
    public RoleDto roleServiceCreate(RoleDto roleDto) {
        if(roleDto!=null){
            RoleEntity roleEntity = dtoToEntity(roleDto);
            repository.save(roleEntity);
            roleDto.setRoleId(roleEntity.getRoleId());
            roleDto.setSystemCreatedDate(roleEntity.getSystemCreatedDate());
        }else{
            throw new MyCustomException("RoleDto is null!");
        }
        return roleDto;
    }


    @Override
    public List<RoleDto> roleServiceList() {
        List<RoleEntity> entityList = repository.findAll();
        List<RoleDto> dtoList = new ArrayList<>();

        for(RoleEntity entity:entityList){
            dtoList.add(entityToDto(entity));
        }

        return dtoList;
    }

    @Override
    @SneakyThrows
    public RoleDto roleServiceFindById(Long id) {

        Boolean doesEntityExist = repository.findById(id).isPresent();

        if(doesEntityExist){
            return entityToDto(repository.findById(id).get());
        }else{
            throw new MyCustomException("No role with "+id+" id!");
        }

    }

    @Override
    @Transactional
    public RoleDto roleServiceUpdateById(Long id, RoleDto roleDto) {
        RoleDto dtoFind = roleServiceFindById(id);
        RoleEntity roleEntity = dtoToEntity(dtoFind);

        // If name value of dto is not null, set the entity name
        if (roleDto.getRoleName() != null) {
            roleEntity.setRoleName(roleDto.getRoleName());
        }
        repository.save(roleEntity);
        roleDto.setRoleId(roleEntity.getRoleId());
        roleDto.setSystemCreatedDate(roleEntity.getSystemCreatedDate());

        return entityToDto(roleEntity);
    }

    @Override
    @Transactional
    @SneakyThrows
    public RoleDto roleServiceDeleteById(Long id) {

        RoleDto dtoFind = roleServiceFindById(id);
        RoleEntity entityToDelete = dtoToEntity(dtoFind);

        if(entityToDelete!=null){
            repository.deleteById(id);
            return dtoFind;

        }else{
            throw new MyCustomException("Can not delete data with id: "+id);
        }


    }
}
