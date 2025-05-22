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

import com.dav.teatri.dto.TeatroServizioDTO;
import com.dav.teatri.service.ServizioService;
import com.dav.teatri.service.TeatroService;
import com.dav.teatri.service.TeatroServizioService;

@Controller
@RequestMapping("/teatro-servizi")
public class TeatroServizioController {

    @Autowired
    private TeatroServizioService service;

    @Autowired
    private TeatroService teatroService;

    @Autowired
    private ServizioService servizioService;

    @GetMapping
    public String list(Model model) {
    	List<TeatroServizioDTO> teatroServizi = service.findAll();
        model.addAttribute("teatroServizi", service.findAll());
        model.addAttribute("listForVis", service.listForVis(teatroServizi));        
        return "teatro_servizi/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("teatroServizio", new TeatroServizioDTO());
        model.addAttribute("teatri", teatroService.findAll());
        model.addAttribute("servizi", servizioService.findAll());
        return "teatro_servizi/form";
    }

    @PostMapping
    public String create(@ModelAttribute TeatroServizioDTO dto) {
        service.create(dto);
        return "redirect:/teatro-servizi";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("teatroServizio", service.findById(id));
        model.addAttribute("teatri", teatroService.findAll());
        model.addAttribute("servizi", servizioService.findAll());
        return "teatro_servizi/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute TeatroServizioDTO dto) {
        service.update(id, dto);
        return "redirect:/teatro-servizi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/teatro-servizi";
    }
}
