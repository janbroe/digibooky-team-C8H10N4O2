package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.users.Feature;
import com.switchfully.digibooky.service.security.SecurityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public void login(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.LOGIN);
    }
}
