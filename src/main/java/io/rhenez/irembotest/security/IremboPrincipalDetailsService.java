package io.rhenez.irembotest.security;

import io.rhenez.irembotest.models.User;
import io.rhenez.irembotest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class IremboPrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        if (user != null) {
            return new IremboUserPrincipal(user);
        }else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
