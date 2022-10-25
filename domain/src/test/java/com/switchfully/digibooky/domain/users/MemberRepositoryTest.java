package com.switchfully.digibooky.domain.users;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    @Test
    void givenAValidMember_whenSaveMemberToRepository_thenAllMembersMustContainGivenMember() {
        MemberRepository memberRepository = new MemberRepository();
        Member givenMember = new Member("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"));
        memberRepository.saveMember(givenMember);
        assertThat(memberRepository.getAll()).isNotNull();
        assertThat(memberRepository.getAll()).isNotEmpty();
        assertThat(memberRepository.getAll()).contains(givenMember);
    }

    @Test
    void givenTwoMembersWithSameEmail_whenSaveMemberToRepository_thenIllegalArgumentExceptionExpected() {
        MemberRepository memberRepository = new MemberRepository();
        Member givenMember = new Member("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"));
        Member givenMember2 = new Member("newINSS2", "lastName2", "password", "email@mail.com", new Address("Brussels2"));
        memberRepository.saveMember(givenMember);
        assertThatThrownBy(() -> memberRepository.saveMember(givenMember2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email address already exists");
    }

    @Test
    void givenTwoMembersWithSameInss_whenSaveMemberToRepository_thenIllegalArgumentExceptionExpected() {
        MemberRepository memberRepository = new MemberRepository();
        Member givenMember = new Member("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"));
        Member givenMember2 = new Member("newINSS", "lastName2", "password", "email2@mail.com", new Address("Brussels2"));
        memberRepository.saveMember(givenMember);
        assertThatThrownBy(() -> memberRepository.saveMember(givenMember2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("This INSS is already in use.");
    }

    @Test
    void doesMemberExist() {
    }

}