package com.dav.teatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dav.teatri.dto.ServizioDTO;
import com.dav.teatri.service.ServizioService;

@Controller
@RequestMapping("/servizi")
public class ServizioController {

    @Autowired
    private ServizioService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("servizi", service.findAll());
        return "servizi/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("servizio", new ServizioDTO());
        return "servizi/form";
    }

    @PostMapping
    public String create(@ModelAttribute ServizioDTO dto) {
        service.create(dto);
        return "redirect:/servizi";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("servizio", service.findById(id));
        return "servizi/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ServizioDTO dto) {
        service.update(id, dto);
        return "redirect:/servizi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/servizi";
    }
}

