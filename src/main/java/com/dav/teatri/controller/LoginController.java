package com.dav.teatri.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.repository.CompagniaAttorialeRepository;

@Controller
public class LoginController {

    @Autowired
    private CompagniaAttorialeRepository compagniaRepo;

    @PostMapping("/login")
    public String login(@RequestParam String nome, @RequestParam String codiceIscrizione, Model model) {
        Optional<CompagniaAttoriale> compagnia = compagniaRepo.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);
        if (compagnia.isPresent()) {
            model.addAttribute("compagnia", compagnia.get());
            return "redirect:/teatri/teatri_list?compagniaId=" + compagnia.get().getId();
        }
        model.addAttribute("errore", "Credenziali non valide");
        return "login";
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}

