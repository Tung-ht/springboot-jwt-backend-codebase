package com.sapo.mock.techshop.mapper;

import java.util.List;

/**
 * Generic Mapper uses for mapping between DTO and Entity.
 * If you want to use custom mapping, you can extend this class and define your own mapping.
 *
 * @param <D> DTO extends AbstractDTO
 * @param <E> Entity extends AbstractEntity
 */
public interface GenericMapper<D, E> {

    D toDto(E entity);

    E createEntity(D dto);

    void updateEntity(E entity, D dto);

}

