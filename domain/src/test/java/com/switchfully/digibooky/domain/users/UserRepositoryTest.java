package com.switchfully.digibooky.domain.users;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserRepositoryTest {

    @Test
    void givenAValidMember_whenSaveMemberToRepository_thenAllMembersMustContainGivenMember() {
        UserRepository userRepository = new UserRepository();
        User givenUser = new User("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"), Role.MEMBER);
        userRepository.saveMember(givenUser);
        assertThat(userRepository.getAll()).isNotNull();
        assertThat(userRepository.getAll()).isNotEmpty();
        assertThat(userRepository.getAll()).contains(givenUser);
    }

    @Test
    void givenTwoMembersWithSameEmail_whenSaveMemberToRepository_thenIllegalArgumentExceptionExpected() {
        UserRepository userRepository = new UserRepository();
        User givenUser = new User("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"), Role.MEMBER);
        User givenUser2 = new User("newINSS2", "lastName2", "password", "email@mail.com", new Address("Brussels2"), Role.MEMBER);
        userRepository.saveMember(givenUser);
        assertThatThrownBy(() -> userRepository.saveMember(givenUser2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email address already exists");
    }

    @Test
    void givenTwoMembersWithSameInss_whenSaveMemberToRepository_thenIllegalArgumentExceptionExpected() {
        UserRepository userRepository = new UserRepository();
        User givenUser = new User("newINSS", "lastName", "password", "email@mail.com", new Address("Brussels"), Role.MEMBER);
        User givenUser2 = new User("newINSS", "lastName2", "password", "email2@mail.com", new Address("Brussels2"), Role.MEMBER);
        userRepository.saveMember(givenUser);
        assertThatThrownBy(() -> userRepository.saveMember(givenUser2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("This INSS is already in use.");
    }

    @Test
    void doesMemberExist() {
    }

}