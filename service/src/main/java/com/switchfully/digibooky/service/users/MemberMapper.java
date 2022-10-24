package com.switchfully.digibooky.service.users;

import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.service.users.dto.CreateMemberDTO;
import com.switchfully.digibooky.service.users.dto.MemberDTO;

public class MemberMapper {

    public MemberDTO mapMemberToDTO(Member member) {
        return new MemberDTO()
                .setFirstname(member.getFirstname())
                .setLastname(member.getLastname())
                .setEmail(member.getEmail())
                .setAddress(member.getAddress());
    }

    public Member mapCreateDTOTOMember(CreateMemberDTO createMemberDTO) {
        return new Member(createMemberDTO.getInss(), createMemberDTO.getFirstname(), createMemberDTO.getLastname(),createMemberDTO.getEmail(),createMemberDTO.getAddress());
    }

}
