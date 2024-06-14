package com.animon.business.service;

import java.util.List;

public interface IRoleService<D,E> {

    public D entityToDto(E e);

    public E dtoToEntity(D d);

    public D roleServiceCreate(D d);

    public List<D> roleServiceList();

    public D roleServiceFindById(Long id);

    public D roleServiceUpdateById(Long id, D d);

    public D roleServiceDeleteById(Long id);

}
