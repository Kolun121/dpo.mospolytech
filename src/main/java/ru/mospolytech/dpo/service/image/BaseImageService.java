package ru.mospolytech.dpo.service.image;

public interface BaseImageService<T, ID> {

    T save(T object);

    void delete(T object);

    void deleteByTId(ID id);
}
