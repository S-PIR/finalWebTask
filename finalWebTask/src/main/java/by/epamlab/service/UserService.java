package by.epamlab.service;

import by.epamlab.dto.UserDto;
import by.epamlab.model.beans.User;
import by.epamlab.validation.user.EmailExistsException;

public interface UserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
