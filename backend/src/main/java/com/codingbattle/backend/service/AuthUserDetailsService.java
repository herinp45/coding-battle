package com.codingbattle.backend.service;


import com.codingbattle.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.codingbattle.backend.model.User;



@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    /**
     * Constructor for AuthUserDetailsService.
     * @param userRepo the repository for user data
     */
    @Autowired
    public AuthUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Loads user details by username or email.
     *
     * @param identifier the username or email of the user
     * @return UserDetails containing the user's details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(identifier, identifier);

        // If user is not found, throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found:" + identifier);
        }

        // Convert user roles to Spring Security roles
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(String.valueOf(user.getRole())) // "USER" or "ADMIN"
                .build();
    }
}
