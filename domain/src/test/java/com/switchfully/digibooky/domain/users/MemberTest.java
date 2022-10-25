package com.switchfully.digibooky.domain.users;

import com.switchfully.digibooky.domain.PasswordHasher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void whenGivenAValidMember_IfDoingValidEmailCheckAndLastNameCheck_GetPositiveResult() {
        Member aMember = new Member("eenINSSNummer", "Jan", "Achternaam", "Passwort", "jan.achternaam@gmail.be", new Address("Parking"));
        assertThat(aMember.getEmail()).isNotNull();
        assertThat(aMember.getLastname()).isNotNull();
        assertThat(aMember.getFirstname()).isEqualTo("Jan");
        assertThat(aMember.getAddress().getCity()).isNotNull();
    }

    @Test
    void whenGivenANotValidEmail_IfDoingValidEmailCheck_ThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new Member("eenINSSNummer", "Jan", "Achternaam","mot de passe", "jan.achternaamgmail.be", new Address("Parking")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong email format. Please review your input.");
    }

    @Test
    void whenGivenANotNoEmail_IfDoingValidEmailCheck_ThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new Member("eenINSSNummer", "Jan", "Achternaam", "password", null, new Address("Parking")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No email address was given.");
    }

    @Test
    void whenCreatingAMember_NotNull() {
        Member aMember = new Member("eenINSSNummer", "Jan", "Achternaam", "password", "jan.achternaam@gmail.be", new Address("Parking"));
        assertThat(aMember).isNotNull();
    }

    @Test
    void whenCreatingAMemberWithoutFirstname_NotNull() {
        Member aMember = new Member("eenINSSNummer", "Achternaam", "password", "jan.achternaam@gmail.be", new Address("Parking"));
        assertThat(aMember).isNotNull();
    }

    @Test
    void whenCreatingAMemberWithNullForLastname_ThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new Member("eenINSSNummer", null, "passwoooooord", "jan@parking.be", new Address("Parking")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Please provide a lastname.");
    }

    @Test
    void whenCreatingAMemberWithNullForCity_ThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new Member("eenINSSNummer", "Jan", "Denull","Pasuwaaado", "jan@parking.be", new Address(null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Please provide a valid input for city.");
    }

    @Test
    void whenCreatingAMemberWithPassword_GivenPasswordHasher_ReturnsGoodHash() {
        Member aMember = new Member("eenINSSNummer", "Jan", "Achternaam","mot de passe", "jan.achternaam@gmail.be", new Address("Parking"));
        assertThat(aMember.getPassword()).isEqualTo(new PasswordHasher("mot de passe").getHashedPassword());
    }
}

