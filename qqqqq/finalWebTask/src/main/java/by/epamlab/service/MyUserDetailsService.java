package by.epamlab.service;

import by.epamlab.model.beans.MyUserDetails;
import by.epamlab.model.beans.User;
import by.epamlab.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);


    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            LOGGER.info("User " + username +  " not found.");
            throw new UsernameNotFoundException("User not found.");
        }
        return new MyUserDetails(user);
    }
}
