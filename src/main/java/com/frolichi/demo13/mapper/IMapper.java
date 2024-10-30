package com.frolichi.demo13.mapper;

public interface IMapper<D, E> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}