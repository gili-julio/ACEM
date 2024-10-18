package com.web2.acem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web2.acem.models.Item;
import com.web2.acem.repository.ItemRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String listItens(Model model) {
        model.addAttribute("itens", itemRepository.findAll());
        return "itens/index";
    }

    @GetMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String formItem(Model model) {
        model.addAttribute("item", new Item());
        return "itens/form";
    }

    @PostMapping("/cadastrar")
    public String saveItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/itens";
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão foi bem-sucedida
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se o item não for encontrado
        }
    }

}
