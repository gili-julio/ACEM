package com.web2.acem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listarItens(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Item> itensPage = itemRepository.findAll(pageable);

        model.addAttribute("itens", itensPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itensPage.getTotalPages());
        return "itens/index";
    }

    @GetMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String formItem(Model model) {
        model.addAttribute("item", new Item());
        return "itens/form";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/itens";
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão foi bem-sucedida
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se o item não for encontrado
        }
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String formEditItem(@PathVariable Long id, Model model) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return "redirect:/itens?itemNotFound=true";
        }
        Item itemOriginal = optionalItem.get();

        model.addAttribute("item", itemOriginal);
        return "itens/editForm";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editItem(@PathVariable Long id, @ModelAttribute Item item) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (!optionalItem.isPresent()) {
            return "redirect:/itens?itemNotFound=true";
        }

        Item itemOriginal = optionalItem.get();
        itemOriginal.setName(item.getName());
        itemOriginal.setDescription(item.getDescription());
        itemOriginal.setAmount(item.getAmount());
        itemRepository.save(itemOriginal);

        return "redirect:/itens?itemEditSuccessful=true";
    }

}
