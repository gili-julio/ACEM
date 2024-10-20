package com.web2.acem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.web2.acem.models.ERole;
import com.web2.acem.models.Role;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Conseguir pegar ROLE_ADMIN com sucesso por Name")
    void testFindByNameCase1() {
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);
        this.createRole(role);

        Optional<Role> result = this.roleRepository.findByName(ERole.ROLE_ADMIN);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Conseguir pegar ROLE_USER com sucesso por Name")
    void testFindByNameCase2() {
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        this.createRole(role);

        Optional<Role> result = this.roleRepository.findByName(ERole.ROLE_USER);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Não conseguir pegar ROLE_ADMIN por Name quando não existir")
    void testFindByNameCase3() {
        Optional<Role> result = this.roleRepository.findByName(ERole.ROLE_ADMIN);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Não conseguir pegar ROLE_USER por Name quando não existir")
    void testFindByNameCase4() {
        Optional<Role> result = this.roleRepository.findByName(ERole.ROLE_USER);

        assertThat(result.isEmpty()).isTrue();
    }

    private Role createRole(Role role) {
        this.entityManager.persist(role);
        return role;
    }
}
