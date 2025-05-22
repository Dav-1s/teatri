package com.dav.teatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.service.TeatroService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/teatri")
public class TeatroController {

    @Autowired
    
    private TeatroService service;
    
    @GetMapping("/teatri_list")
    public String listaTeatri(@RequestParam Long compagniaId, Model model) {
    	System.out.println(">>>>> CHIAMATO /teatri_list con compagniaId = " + compagniaId);
        model.addAttribute("teatri", service.findAll());
        model.addAttribute("compagniaId", compagniaId);
        return "teatri_list";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("teatri", service.findAll());
        return "teatri/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("teatro", new TeatroDTO());
        return "teatri/form";
    }

    @PostMapping
    public String create(@ModelAttribute TeatroDTO dto) {
        service.create(dto);
        return "redirect:/teatri";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("teatro", service.findById(id));
        return "teatri/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute TeatroDTO dto) {
        service.update(id, dto);
        return "redirect:/teatri";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/teatri";
    }
}

