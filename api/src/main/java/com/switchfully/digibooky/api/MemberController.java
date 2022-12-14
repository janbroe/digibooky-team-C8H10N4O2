package com.switchfully.digibooky.api;

import com.switchfully.digibooky.service.users.UserService;
import com.switchfully.digibooky.service.users.dto.CreateUserDTO;
import com.switchfully.digibooky.service.users.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members")
@CrossOrigin
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    private final UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO SaveMember(@RequestBody CreateUserDTO createUserDTO) {
        log.debug("POST -> Controller post a new member");
        return userService.saveMember(createUserDTO);
    }


}
