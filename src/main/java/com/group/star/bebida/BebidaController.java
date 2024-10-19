package com.group.star.bebida;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bebidas")
public class BebidaController {

    private final BebidaRepository bebidaRepository;

    public BebidaController(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @GetMapping("")
    public String findAll(@RequestParam(value = "sort", required = false, defaultValue = "id") String sort, Model model) {
        List<Bebida> bebidas;

        // Apply sorting based on the provided sort parameter
        if ("name".equals(sort)) {
            bebidas = bebidaRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        } else if ("type".equals(sort)) {
            bebidas = bebidaRepository.findAll(Sort.by(Sort.Direction.ASC, "type"));
        } else {
            bebidas = bebidaRepository.findAll(Sort.by(Sort.Direction.ASC, "id")); // Default to sorting by ID
        }

        model.addAttribute("bebidas", bebidas);
        model.addAttribute("sort", sort); // Pass the current sort option to the view
        return "list-bebidas";
    }

    @GetMapping("/new")
    public String createBebidaForm(Model model) {
        model.addAttribute("bebida", new Bebida());
        model.addAttribute("types", BebidaType.values()); // Add this line for types
        return "edit-bebidas"; // returns the edit-bebidas.html template
    }

    @PostMapping
    public String createBebida(@ModelAttribute Bebida bebida) {
        bebidaRepository.save(bebida);
        return "redirect:/bebidas"; // redirects to the list page
    }

    @GetMapping("/edit/{id}")
    public String editBebida(@PathVariable Long id, Model model) {
        Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(() -> new BebidaNotFoundException());
        model.addAttribute("bebida", bebida);
        model.addAttribute("types", BebidaType.values()); // Add this line for types
        return "edit-bebidas"; // returns the edit-bebidas.html template
    }

    @PostMapping("/edit/{id}")
    public String updateBebida(@PathVariable Long id, @ModelAttribute Bebida bebida) {
        // Ensure the ID is set on the bebida object
        bebida.setId(id); // Set the ID of the bebida being updated
        bebidaRepository.save(bebida); // This will update the existing bebida
        return "redirect:/bebidas"; // redirects to the list page
    }

    @GetMapping("/delete/{id}")
    public String deleteBebida(@PathVariable Long id) {
        bebidaRepository.deleteById(id);
        return "redirect:/bebidas"; // redirects to the list page
    }
}
