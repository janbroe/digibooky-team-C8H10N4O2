package com.switchfully.digibooky.domain.users;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    private final Map<String, Member> memberRepositoryByID;

    public MemberRepository() {
        this.memberRepositoryByID = new HashMap<>();
        Member member1 = new Member("inss1", "first1", "password", "test@test.be", new Address("city1"));
        Member member2 = new Member("inss2", "first2", "password", "tes2@test.be", new Address("city2"));
        Member member3 = new Member("inss3", "first3", "password", "tes3@test.be", new Address("city3"));
        Member member4 = new Member("inss4", "first4", "password", "tes4@test.be", new Address("city4"));
        Member member5 = new Member("inss5", "first5", "password", "tes5@test.be", new Address("city5"));
        memberRepositoryByID.put(member1.getUserId(), member1);
        memberRepositoryByID.put(member2.getUserId(), member2);
        memberRepositoryByID.put(member3.getUserId(), member3);
        memberRepositoryByID.put(member4.getUserId(), member4);
        memberRepositoryByID.put(member5.getUserId(), member5);
//        System.out.println("member: " + member1.getUserId());
    }

    public void saveMember(Member member) {
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

    public boolean doesMemberExist(String userID) {
        return memberRepositoryByID.containsKey(userID);
    }

    public Collection<Member> getAll() {
        return memberRepositoryByID.values();
    }
}
