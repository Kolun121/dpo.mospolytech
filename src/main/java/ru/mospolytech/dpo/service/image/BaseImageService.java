package ru.mospolytech.dpo.service.image;

public interface BaseImageService<T, ID> {
    T findById(ID id);
    
    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
