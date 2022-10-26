package com.switchfully.digibooky.service.users;

import com.switchfully.digibooky.domain.users.Role;
import com.switchfully.digibooky.domain.users.User;
import com.switchfully.digibooky.service.users.dto.CreateUserDTO;
import com.switchfully.digibooky.service.users.dto.UserDTO;

public class UserMapper {


    public UserDTO mapUserToDTO(User user) {
        return new UserDTO()
                .setUserId(user.getUserId())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress());
    }

    public User mapCreateUserDTOToMember(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getInss(), createUserDTO.getFirstname(), createUserDTO.getLastname(), createUserDTO.getPassword(), createUserDTO.getEmail(), createUserDTO.getAddress(), Role.MEMBER);
    }

    public User mapCreateUserDTOToLibrarian(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getInss(), createUserDTO.getFirstname(), createUserDTO.getLastname(), createUserDTO.getPassword(), createUserDTO.getEmail(), createUserDTO.getAddress(), Role.LIBRARIAN);
    }
}
