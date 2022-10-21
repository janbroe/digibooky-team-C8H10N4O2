package com.switchfully.digibooky.domain.users;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void whenGivenAValidEmail_IfDoingValidEmailCheck_GetPositiveResult() {
        Member aMember = new Member("eenINSSNummer", "Jan", "Achternaam", "jan.achternaam@gmail.be", new Address("Parking"));
        assertThat(aMember.getEmail()).isNotNull();
    }

    @Test
    void whenGivenANotValidEmail_IfDoingValidEmailCheck_ThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new Member("eenINSSNummer", "Jan", "Achternaam", "jan.achternaamgmail.be", new Address("Parking")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong email format. Please review your input.");
    }
    
}