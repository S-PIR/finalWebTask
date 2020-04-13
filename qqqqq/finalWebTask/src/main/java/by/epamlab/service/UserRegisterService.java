package by.epamlab.service;

import by.epamlab.dto.UserDto;
import by.epamlab.model.beans.Authority;
import by.epamlab.model.beans.AuthorityType;
import by.epamlab.model.beans.User;
import by.epamlab.repositories.UserRepository;
import by.epamlab.validation.user.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Locale;

@Service("userRegisterService")
public class UserRegisterService implements UserService {
    private static final Integer ROLE_USER_ID = 2;

    @Autowired
    private UserRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if(emailOrUsernameExists(accountDto.getUsername(), accountDto.getEmail())){
            throw new EmailExistsException(messageSource.getMessage("UniqueUsername.user.username", null, Locale.ENGLISH));
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setEnabled(true);
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(new Authority(ROLE_USER_ID, AuthorityType.ROLE_USER));
        user.setAuthorities(authorities);
        return repository.add(user);
    }

    private boolean emailOrUsernameExists(String username, String email){
        User userByUsername = repository.getUserByUsername(username);
        User userByEmail = repository.getUserByEmail(email);
        boolean rez = false;
        if(userByUsername != null || userByEmail != null){
            rez = true;
        }
        return rez;
    }
}
