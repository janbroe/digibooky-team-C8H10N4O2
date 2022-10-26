package com.switchfully.digibooky.service.users;

import com.switchfully.digibooky.domain.users.User;
import com.switchfully.digibooky.domain.users.UserRepository;
import com.switchfully.digibooky.service.users.dto.CreateUserDTO;
import com.switchfully.digibooky.service.users.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public UserDTO saveMember(CreateUserDTO createUserDTO) {
        User newMember = userMapper.mapCreateUserDTOToMember(createUserDTO);
        userRepository.saveMember(newMember);
        return userMapper.mapUserToDTO(newMember);
    }

    public UserDTO registerLibrarian(CreateUserDTO createUserDTO) {
        User newLibrarian = userMapper.mapCreateUserDTOToLibrarian(createUserDTO);
        userRepository.registerLibrarian(newLibrarian);
        return userMapper.mapUserToDTO(newLibrarian);
    }
}
