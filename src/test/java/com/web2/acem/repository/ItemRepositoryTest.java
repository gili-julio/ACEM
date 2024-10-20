package com.web2.acem.repository;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.web2.acem.models.Item;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Conseguir pegar todos os Item com sucesso")
    void testFindAll() {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setName("item1");
        item1.setDescription("testando1");
        item1.setAmount(1);
        item2.setName("item2");
        item2.setDescription("testando2");
        item2.setAmount(2);
        item3.setName("item3");
        item3.setDescription("testando3");
        item3.setAmount(3);

        this.createItem(item1);
        this.createItem(item2);
        this.createItem(item3);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> result = this.itemRepository.findAll(pageable);

        assertThat(result.getNumberOfElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("Conseguir pegar Item com sucesso por Name")
    void testFindByNameCase1() {
        String name = "teste";
        Item item = new Item();
        item.setName(name);
        item.setDescription("testando");
        item.setAmount(5);
        this.createItem(item);

        Optional<Item> result = this.itemRepository.findByName(name);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Não conseguir pegar Item por Name quando não existir")
    void testFindByNameCase2() {
        String name = "teste";

        Optional<Item> result = this.itemRepository.findByName(name);

        assertThat(result.isEmpty()).isTrue();
    }

    private Item createItem(Item item) {
        this.entityManager.persist(item);
        return item;
    }
}
