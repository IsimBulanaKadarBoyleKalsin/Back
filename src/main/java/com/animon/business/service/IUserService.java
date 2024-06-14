package com.animon.business.service;

import java.util.List;

public interface IUserService <D,E>{

    // MAPPING
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // CRUD



    public D userServiceCreate(Long rolesId,D d);

    public List<D> userServiceList();

    public D userServiceFindById(Long id);

    public D userServiceUpdateById(Long id, D d);

    public D userServiceDeleteById(Long id);

}
