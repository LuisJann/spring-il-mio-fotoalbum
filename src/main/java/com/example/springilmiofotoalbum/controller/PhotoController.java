package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import com.example.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
