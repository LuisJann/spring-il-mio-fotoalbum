package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import com.example.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model){
        List<Photo> photos;
        photos = photoService.getAllPhotos();
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
        return "/photos/create";
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
}
