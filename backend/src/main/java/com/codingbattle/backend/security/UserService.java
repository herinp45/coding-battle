package com.codingbattle.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import com.codingbattle.backend.repository.UserRepo;

public class UserService {

    /**
     * User repository
     */
    @Autowired
    private final UserRepo userRepo;
    /**
     * User mapper
     */
    @Autowired
    private final UserMapper userMapper;

    /**
     * User service constructor
     * @param userRepo User repository
     * @param userMapper User mapper
     */
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }
    
    /**
     * Find user by id
     * @param id User id
     * @return User
     */
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    public User findAll() {
        return userRepo.findAll();
    }

    /**
     * Save user
     * @param user User
     * @return User
     */
    public User save(UserRequestDTO user) {
        User userEntity = userMapper.toEntity(user);
        return userRepo.save(userEntity);
    }
}