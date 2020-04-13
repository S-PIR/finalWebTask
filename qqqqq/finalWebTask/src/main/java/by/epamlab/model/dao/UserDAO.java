package by.epamlab.model.dao;

import by.epamlab.model.beans.User;

public interface UserDAO {
    User findUserByUsername(String username);
}
