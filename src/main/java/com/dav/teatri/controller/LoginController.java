package com.dav.teatri.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dav.teatri.dto.PrenotazioneDTO;
import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.model.Prenotazione;
import com.dav.teatri.repository.CompagniaAttorialeRepository;
import com.dav.teatri.service.TeatroServizioService;

@Controller
public class LoginController {

    @Autowired
    private CompagniaAttorialeRepository compagniaRepo;
    
    @Autowired
    private TeatroServizioService teatroServizioService;



    @GetMapping("/login")
    public String login(
        @RequestParam String nome,
        @RequestParam String codiceIscrizione,
        Model model
    ) {
        Optional<CompagniaAttoriale> compagniaOpt = compagniaRepo.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);
        
        if (compagniaOpt.isPresent()) {
            Long compagniaId = compagniaOpt.get().getId();
            model.addAttribute("compagniaId", compagniaId);
            return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
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
    @GetMapping("/prenotazioneUtente")
    public String mostraFormPrenotazione(@RequestParam Long compagniaId, Model model) {
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();

        Optional<CompagniaAttoriale> compagnia = compagniaRepo.findById(compagniaId);
        if (compagnia.isEmpty()) {
            model.addAttribute("errore", "Compagnia non trovata");
            return "login";
        }

        prenotazione.setCompagniaId(compagnia.get().getId());

        model.addAttribute("prenotazione", prenotazione);
        model.addAttribute("teatriServizi", teatroServizioService.findAllm());
        return "utentePrenota";
    }
    
    @PostMapping("/prenotazioneSave")
    public String salvaPrenotazione(@ModelAttribute Prenotazione prenotazione, @RequestParam Long compagniaId) {
        Optional<CompagniaAttoriale> compagnia = compagniaRepo.findById(compagniaId);
        compagnia.ifPresent(prenotazione::setCompagnia);

        
        return "redirect:/conferma";
    }
}

