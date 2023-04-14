package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(@RequestParam("id") Optional<Integer> idParam, Model model){
        model.addAttribute("categories", categoryService.getAll());
        if (idParam.isPresent()){
            model.addAttribute("categoryObj", categoryService.getById(idParam.get()));
        }else{
            model.addAttribute("categoryObj", new Category());
        }
        return "/categories/index";
    }

    @PostMapping("/save")
    public String categorySave(@Valid @ModelAttribute(name = "categoryObj") Category category, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAll());
            return "/categories/index";
        }
        if (category.getId() != null){
            categoryService.update(category);
        }else {
            categoryService.create(category);
        }

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = categoryService.deleteById(id);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
            } else {
                redirectAttributes.addFlashAttribute("message", "la cancellazione non è andata a buon fine");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "La categoria con " + id + " non è stata trovata");
        }
        return "redirect:/categories";
    }

}
