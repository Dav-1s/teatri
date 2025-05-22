package com.dav.teatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dav.teatri.dto.CompagniaAttorialeDTO;
import com.dav.teatri.service.CompagniaAttorialeService;

@Controller
@RequestMapping("/compagnie")
public class CompagniaAttorialeController {

    @Autowired
    private CompagniaAttorialeService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("compagnie", service.findAll());
        return "compagnie/list"; // Thymeleaf: templates/compagnie/list.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("compagnia", new CompagniaAttorialeDTO());
        return "compagnie/form";
    }

    @PostMapping
    public String create(@ModelAttribute CompagniaAttorialeDTO dto) {
        service.create(dto);
        return "redirect:/compagnie";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("compagnia", service.findById(id));
        return "compagnie/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute CompagniaAttorialeDTO dto) {
        service.update(id, dto);
        return "redirect:/compagnie";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);

        return "redirect:/compagnie";
    }
}

