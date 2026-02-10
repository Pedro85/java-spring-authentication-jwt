package com.authservice.core.mapper;

import org.springframework.stereotype.Component;

@Component
public interface BaseMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}