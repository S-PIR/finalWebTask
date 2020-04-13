package by.epamlab.repositories;

import by.epamlab.model.beans.User;


public interface UserRepository extends CrudRepository<User, Integer> {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
