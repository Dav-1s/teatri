package com.dav.teatri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dav.teatri.dto.PrenotazioneDTO;
import com.dav.teatri.dto.TeatroDTO;
import com.dav.teatri.dto.TeatroServizioDTO;
import com.dav.teatri.mapper.CompagniaAttorialeMapper;
import com.dav.teatri.model.CompagniaAttoriale;
import com.dav.teatri.service.CompagniaAttorialeService;
import com.dav.teatri.service.PrenotazioneService;
import com.dav.teatri.service.ServizioService;
import com.dav.teatri.service.TeatroService;
import com.dav.teatri.service.TeatroServizioService;

@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    @Autowired
    private CompagniaAttorialeService compagniaService;

    @Autowired
    private TeatroServizioService teatroServizioService;
    
    @Autowired
    private TeatroService teatroService;

    @Autowired
    private ServizioService servizioService;


    @Autowired
    private PrenotazioneService prenotazioneService;
    
    @GetMapping("/seleziona-servizio")
    public String selezionaServizio(@RequestParam Long teatroId, Model model) {
        
        TeatroDTO teatroDto = teatroService.findById(teatroId);
        model.addAttribute("teatro", teatroDto);
        
        
        model.addAttribute("servizi", servizioService.findAll());
        
        
        return "prenotazioni/seleziona-servizio";  
    }


    @GetMapping("/teatri")
    public String listaTeatri(Model model) {
        model.addAttribute("teatri", teatroService.findAll());
        return "prenotazioni/teatri";
    }

    @GetMapping("/nuova/{idTeatro}")
    public String mostraFormPrenotazione(@PathVariable Long idTeatro, Model model) {
        model.addAttribute("servizi", servizioService.findAll());
        model.addAttribute("prenotazione", new PrenotazioneDTO());
        model.addAttribute("idTeatro", idTeatro);
        return "prenotazioni/form";
    }

    @PostMapping("/salva")
    public String salvaPrenotazione(@ModelAttribute PrenotazioneDTO prenotazioneDTO) {
        prenotazioneService.create(prenotazioneDTO);
        return "redirect:/";
    }


    @GetMapping
    public String list(Model model) {
    	List<PrenotazioneDTO> prenotazioni = service.findAll();
        model.addAttribute("prenotazioni", service.findAll());
        model.addAttribute("listForVispr", service.listForVis(prenotazioni));
        return "prenotazioni/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("prenotazione", new PrenotazioneDTO());
        model.addAttribute("compagnie", compagniaService.findAll());
        model.addAttribute("teatroServizi", teatroServizioService.findAll());
        List<TeatroServizioDTO> teatroServizi = teatroServizioService.findAll();
        model.addAttribute("teatroServizi", service.findAll());
        model.addAttribute("listForVis", teatroServizioService.listForVis(teatroServizi));
        return "prenotazioni/form";
    }

    @PostMapping
    public String create(@ModelAttribute PrenotazioneDTO dto, RedirectAttributes redirectAttributes) {
    	// TODO aggiungere messaggio di errore per data duplicata
    	Long compagniaId = dto.getCompagniaId();
    	String teatroOccupato = service.teatroOccupato(dto);
    	if (teatroOccupato == null) {
    		service.create(dto);
    	} else {
    		redirectAttributes.addFlashAttribute("errore", teatroOccupato);
    	}
    	return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
        
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	List<PrenotazioneDTO> prenotazioni = service.findAll();
        model.addAttribute("prenotazione", service.findById(id));
        model.addAttribute("compagnie", compagniaService.findAll());
        model.addAttribute("teatroServizi", teatroServizioService.findAllm());
        model.addAttribute("listForVispr", service.listForVis(prenotazioni));
        return "prenotazioni/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute PrenotazioneDTO dto) {
        service.update(id, dto);
        return "redirect:/prenotazioni";
    }
    
    
    
    @GetMapping("/editu/{id}")
    public String showEditFormu(@PathVariable Long id, Model model) {

    	Long compagniaId = service.findById(id).getCompagniaId();
    	
    	List<PrenotazioneDTO> prenotazioni = service.findAll();
        model.addAttribute("prenotazione", service.findById(id));
        model.addAttribute("compagniaId", compagniaId);
        model.addAttribute("teatroServizi", teatroServizioService.findAllm());
        model.addAttribute("listForVispr", service.listForVis(prenotazioni));
        
        return "prenotazioni/formu";
    }

    @PostMapping("u/{id}")
    public String updateu(@PathVariable Long id, @ModelAttribute PrenotazioneDTO dto) {
    	Long compagniaId = service.findById(id).getCompagniaId();
        service.update(id, dto);
        return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/prenotazioni";
    }
    
    @GetMapping("/deleteu/{id}")
    public String deleteu(@PathVariable Long id) {
    	Long compagniaId = service.findById(id).getCompagniaId();
        service.delete(id);
        return "redirect:/prenotazioneUtente?compagniaId=" + compagniaId;
    }
}

