package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.service.dto.MemberDTO;

public class MemberMapper {

    public MemberDTO mapMemberToDTO(Member member) {
        return new MemberDTO()
                .setFirstname(member.getFirstname())
                .setLastname(member.getLastname())
                .setEmail(member.getEmail())
                .setAddress(member.getAddress());
    }

}
