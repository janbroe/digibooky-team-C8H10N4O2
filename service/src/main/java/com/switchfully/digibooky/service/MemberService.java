package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.domain.users.MemberRepository;
import com.switchfully.digibooky.service.dto.CreateMemberDTO;
import com.switchfully.digibooky.service.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = new MemberMapper();
    }

    public MemberDTO saveMember(CreateMemberDTO createMemberDTO) {
        Member newMember = new Member(createMemberDTO.getInss(), createMemberDTO.getFirstname(), createMemberDTO.getLastname(),createMemberDTO.getEmail(),createMemberDTO.getAddress());
        memberRepository.saveMember(newMember);
        return memberMapper.mapMemberToDTO(newMember);
    }

}