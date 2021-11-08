package com.khodko.webapi.webapi.mapper;

public interface BaseMapper<ENTITY, DTO> {

    DTO entityToDto(ENTITY entity);

    ENTITY dtoToEntity(DTO entity);
}
