package com.dav.teatri.controller;

import java.util.List;
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
import com.dav.teatri.service.CompagniaAttorialeService;
import com.dav.teatri.service.PrenotazioneService;
import com.dav.teatri.service.TeatroServizioService;

@Controller
public class LoginController {

    @Autowired
    private CompagniaAttorialeService compagniaService;
    
    @Autowired
    private TeatroServizioService teatroServizioService;
    

    @Autowired
    private PrenotazioneService prenotazioneService;

//    @GetMapping("/login")
//    public String login(
//        @RequestParam String nome,
//        @RequestParam String codiceIscrizione,
//        Model model
//    ) {
//        Optional<CompagniaAttoriale> compagniaOpt = compagniaService.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);
//        
//        if (compagniaOpt.get().getId() == 0) {
//        	return "redirect:/index";
//        }
//        else if (compagniaOpt.isPresent()) {
//            Long compagniaId = compagniaOpt.get().getId();
//            model.addAttribute("compagniaId", compagniaId);
//            model.addAttribute("nomeCompagnia", compagniaService.findById(compagniaId).getNome());
//            return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
//        }
//        else {
//            model.addAttribute("errore", "Credenziali non valide");
//            return "login";
//        }
//
//
//    }
    
    @GetMapping("/login")
    public String login(
        @RequestParam String nome,
        @RequestParam String codiceIscrizione,
        Model model
    ) {
        Optional<CompagniaAttoriale> compagniaOpt = compagniaService.findByNomeAndCodiceIscrizione(nome, codiceIscrizione);

        if (compagniaOpt.isEmpty()) {
            model.addAttribute("errore", "Credenziali non valide");
            return "login";
        }

        CompagniaAttoriale compagnia = compagniaOpt.get();
        
        // Se vuoi ancora controllare se id == 0 (anche se strano per un DB), fallo qui
        if (compagnia.getId() == 0) {
            return "redirect:/index";
        }

        Long compagniaId = compagnia.getId();
        model.addAttribute("compagniaId", compagniaId);
        model.addAttribute("nomeCompagnia", compagnia.getNome());

        return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
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
    public String mostraFormPrenotazione(@RequestParam Long compagniaId, Model model, @ModelAttribute("errore") String errore) {
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        
        List<Prenotazione> prenotazioniCompagnia = prenotazioneService.findByCompagniaId(compagniaId);

        Optional<CompagniaAttoriale> compagnia = compagniaService.findByIdOpt(compagniaId);
        if (compagnia.isEmpty()) {
            model.addAttribute("errore", "Compagnia non trovata");
            return "login";
        }

        prenotazione.setCompagniaId(compagnia.get().getId());

        model.addAttribute("prenotazione", prenotazione);
        model.addAttribute("teatriServizi", teatroServizioService.findAllm());
        model.addAttribute("prenotazioniCompagnia", prenotazioniCompagnia);
        
        if (errore != null && !errore.isEmpty()) {
            model.addAttribute("errore", errore);
        }
        
        return "utentePrenota";
    }
    
    @PostMapping("/prenotazioneSave")
    public String salvaPrenotazione(@ModelAttribute Prenotazione prenotazione, @RequestParam Long compagniaId) {
        Optional<CompagniaAttoriale> compagnia = compagniaService.findByIdOpt(compagniaId);
        compagnia.ifPresent(prenotazione::setCompagnia);

        
        return "redirect:/conferma";
    }
}

