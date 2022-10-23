package com.switchfully.digibooky.api;

import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.service.MemberService;
import com.switchfully.digibooky.service.dto.CreateMemberDTO;
import com.switchfully.digibooky.service.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO SaveMember(@RequestBody CreateMemberDTO createMemberDTO) {
        log.debug("POST -> Controller post a new member");
        return memberService.saveMember(createMemberDTO);
    }
}
