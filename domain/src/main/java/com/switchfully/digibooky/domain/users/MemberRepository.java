package com.switchfully.digibooky.domain.users;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {

    private final Map<String, Member> memberRepositoryByID;

    public MemberRepository() {
        this.memberRepositoryByID = new HashMap<>();
    }

    public void saveMember(Member member){
        if (!isEmailOfMemberUnique(member.getEmail())) {
            throw new IllegalArgumentException("Email address already exists");
        }
        if (!isInssOfMemberUnique(member.getInss())) {
            throw new IllegalArgumentException("This INSS is already in use.");
        }
        memberRepositoryByID.put(member.getUserId(), member);
    }

    public boolean isEmailOfMemberUnique(String email) {
        for (Member member : memberRepositoryByID.values()) {
            if (member.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean isInssOfMemberUnique(String inss) {
        for (Member member : memberRepositoryByID.values()) {
            if (member.getInss().equals(inss)) {
                return false;
            }
        }
        return true;
    }
}
