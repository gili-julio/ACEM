package com.web2.acem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.web2.acem.models.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Conseguir pegar User com sucesso por Username")
    void testFindByUsernameCase1() {
        String username = "teste";
        User user = new User();
        user.setUsername(username);
        user.setPassword(username);
        user.setRoles(null);
        this.createUser(user);

        Optional<User> result = this.userRepository.findByUsername(username);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Não conseguir pegar User por Username quando não existir")
    void testFindByUsernameCase2() {
        String username = "teste";

        Optional<User> result = this.userRepository.findByUsername(username);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(User user) {
        this.entityManager.persist(user);
        return user;
    }
}
