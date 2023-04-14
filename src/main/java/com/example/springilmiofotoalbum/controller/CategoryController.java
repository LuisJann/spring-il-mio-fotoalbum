package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
}
