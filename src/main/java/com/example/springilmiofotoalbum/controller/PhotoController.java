package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import com.example.springilmiofotoalbum.service.CategoryService;
import com.example.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "input") Optional<String> keyword){
        List<Photo> photos;
        if (keyword.isPresent()){
            photos = photoService.getFilteredPhoto(keyword.get());
            model.addAttribute("keyword", keyword);
        }else {
            photos = photoService.getAllPhotos();
        }
        model.addAttribute("photos", photos);
        return "photos/index";
    }

    @GetMapping("/photos/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model){
        try{
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id" + id + "not found");
        }
    }

    @GetMapping("/photos/create")
    public String create(Model model){
        model.addAttribute("photo", new Photo());
        model.addAttribute("categoryList", categoryService.getAll());
        return "/photos/edit";
    }

    @PostMapping("/photos/create")
    public String addPhoto(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/photos/create";
        }else {
            photoService.createPhoto(formPhoto);
            return "redirect:/";
        }
    }

    @GetMapping("/photos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try{
            Photo photo = photoService.getById(id);
            model.addAttribute("photo",photo);
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/edit";
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id" + id + " not found");
        }
    }

    @PostMapping("/photos/edit/{id}")
    public String editPhoto(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/edit";
        }
        try{
            Photo updatePhoto = photoService.updatePhoto(formPhoto, id);
            return "redirect:/";
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id" + id + " not found");
        }
    }

    @GetMapping("/photos/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            boolean check = photoService.deleteById(id);
            if (check){
                redirectAttributes.addFlashAttribute("message", "Photo with id:" + id +" delete");
            }else{
                redirectAttributes.addFlashAttribute("message", "Unable to delete photo whit id: " + id);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/";
    }
}
