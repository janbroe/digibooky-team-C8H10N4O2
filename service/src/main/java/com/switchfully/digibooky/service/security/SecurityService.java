package com.switchfully.digibooky.service.security;

import com.switchfully.digibooky.domain.exceptions.UnauthorizedException;
import com.switchfully.digibooky.domain.exceptions.WrongPasswordException;
import com.switchfully.digibooky.domain.users.Feature;
import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.domain.users.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class SecurityService {
    private final Logger log = LoggerFactory.getLogger(SecurityService.class);
    private final MemberRepository memberRepository;

    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        Member member = memberRepository.getMemberByEmail(usernamePassword.getUsername());
        if (member == null) {
            log.error("Unknown user" + usernamePassword.getUsername());
            throw new NoSuchElementException("Username does not exist");
        }
        if (!member.doesPasswordMatch(usernamePassword.getPassword())) {
            log.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if (!member.canHaveAccessTo(feature)) {
            log.error("User " + usernamePassword.getUsername() + " does not have access to " + feature);
            throw new UnauthorizedException();
        }
    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}
