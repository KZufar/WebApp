package ru.ivmiit.repository;

import ru.ivmiit.models.User;

import java.util.List;

/**
 * 05.10.2017
 * CrudRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

/**
 * Шаблон репозитория с операциями CRUD (Create, Read, Update, Delete)
 * @param <T> - тип модели, с которой будет работать конкретный репозиторий
 */
public interface CrudRepository<T> {
    List<User> save(T model);
    void delete(Long id);
    void update(T model);
    T find(Long id);
    List<T> findAll();
}
