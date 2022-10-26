package com.switchfully.digibooky.domain.users;

import com.switchfully.digibooky.domain.PasswordHasher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    @Test
    void whenGivenAValidMember_IfDoingValidEmailCheckAndLastNameCheck_GetPositiveResult() {
        User aUser = new User("eenINSSNummer", "Jan", "Achternaam", "Passwort", "jan.achternaam@gmail.be", new Address("Parking"), Role.MEMBER);
        assertThat(aUser.getEmail()).isNotNull();
        assertThat(aUser.getLastname()).isNotNull();
        assertThat(aUser.getFirstname()).isEqualTo("Jan");
        assertThat(aUser.getAddress().getCity()).isNotNull();
    }

    @Test
    void whenGivenANotValidEmail_IfDoingValidEmailCheck_ThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new User("eenINSSNummer", "Jan", "Achternaam","mot de passe", "jan.achternaamgmail.be", new Address("Parking"), Role.MEMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong email format. Please review your input.");
    }

    @Test
    void whenGivenANotNoEmail_IfDoingValidEmailCheck_ThrowIllegalArgumentException() {
        assertThatThrownBy(() -> new User("eenINSSNummer", "Jan", "Achternaam", "password", null, new Address("Parking"), Role.MEMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No email address was given.");
    }

    @Test
    void whenCreatingAMember_NotNull() {
        User aUser = new User("eenINSSNummer", "Jan", "Achternaam", "password", "jan.achternaam@gmail.be", new Address("Parking"), Role.MEMBER);
        assertThat(aUser).isNotNull();
    }

    @Test
    void whenCreatingAMemberWithoutFirstname_NotNull() {
        User aUser = new User("eenINSSNummer", "Achternaam", "password", "jan.achternaam@gmail.be", new Address("Parking"), Role.MEMBER);
        assertThat(aUser).isNotNull();
    }

    @Test
    void whenCreatingAMemberWithNullForLastname_ThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new User("eenINSSNummer", null, "passwoooooord", "jan@parking.be", new Address("Parking"), Role.MEMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Please provide a lastname.");
    }

    @Test
    void whenCreatingAMemberWithNullForCity_ThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new User("eenINSSNummer", "Jan", "Denull","Pasuwaaado", "jan@parking.be", new Address(null), Role.MEMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Please provide a valid input for city.");
    }

    @Test
    void whenCreatingAMemberWithPassword_GivenPasswordHasher_ReturnsGoodHash() {
        User aUser = new User("eenINSSNummer", "Jan", "Achternaam","mot de passe", "jan.achternaam@gmail.be", new Address("Parking"), Role.MEMBER);
        assertThat(aUser.getPassword()).isEqualTo(new PasswordHasher("mot de passe").getHashedPassword());
    }
}

