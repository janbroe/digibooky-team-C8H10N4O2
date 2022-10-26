package com.switchfully.digibooky.domain.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final Map<String, User> memberRepositoryByID;

    public UserRepository() {
        this.memberRepositoryByID = new HashMap<>();
        User god = new User(null, "god", "password", "god@lib.be", new Address("virtual space"), Role.ADMIN);
        User librarian1 = new User("inss0", "first0", "password", "test@lib.be", new Address("city0"), Role.LIBRARIAN);
        User user1 = new User("inss1", "first1", "password", "test@test.be", new Address("city1"), Role.MEMBER);
        User user2 = new User("inss2", "first2", "password", "tes2@test.be", new Address("city2"), Role.MEMBER);
        User user3 = new User("inss3", "first3", "password", "tes3@test.be", new Address("city3"), Role.MEMBER);
        User user4 = new User("inss4", "first4", "password", "tes4@test.be", new Address("city4"), Role.MEMBER);
        User user5 = new User("inss5", "first5", "password", "tes5@test.be", new Address("city5"), Role.MEMBER);
        memberRepositoryByID.put(god.getUserId(), god);
        memberRepositoryByID.put(librarian1.getUserId(), librarian1);
        memberRepositoryByID.put(user1.getUserId(), user1);
        memberRepositoryByID.put(user2.getUserId(), user2);
        memberRepositoryByID.put(user3.getUserId(), user3);
        memberRepositoryByID.put(user4.getUserId(), user4);
        memberRepositoryByID.put(user5.getUserId(), user5);
    }

    public void saveMember(User user) {
        if (!isEmailOfMemberUnique(user.getEmail())) {
            throw new IllegalArgumentException("Email address already exists");
        }
        if (!isInssOfMemberUnique(user.getInss())) {
            throw new IllegalArgumentException("This INSS is already in use.");
        }
        memberRepositoryByID.put(user.getUserId(), user);
        log.info("POST -> ".concat(user.toString()));
    }

    public void registerLibrarian(User librarian) {
        if (!isEmailOfMemberUnique(librarian.getEmail())) {
            throw new IllegalArgumentException("Email address already exists");
        }
        memberRepositoryByID.put(librarian.getUserId(), librarian);
        log.info("POST -> ".concat(librarian.toString()));
    }

    public boolean isEmailOfMemberUnique(String email) {
        for (User user : memberRepositoryByID.values()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean isInssOfMemberUnique(String inss) {
        for (User user : memberRepositoryByID.values()) {
            if (user.getInss().equals(inss)) {
                return false;
            }
        }
        return true;
    }

    public boolean doesMemberExist(String userID) {
        return memberRepositoryByID.containsKey(userID);
    }

    public Collection<User> getAll() {
        return memberRepositoryByID.values();
    }

    public User getMemberByEmail(String emailAsUsername) {
        for (User user : memberRepositoryByID.values()) {
            if (user.getEmail().equals(emailAsUsername)) {
                return user;
            }
        }
        throw new NoSuchElementException("Username ".concat(emailAsUsername).concat("  was not found"));
    }

}
