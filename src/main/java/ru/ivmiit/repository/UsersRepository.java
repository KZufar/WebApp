package ru.ivmiit.repository;

import ru.ivmiit.models.User;

import java.util.List;

/**
 * 05.10.2017
 * UsersRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(int age);
}
