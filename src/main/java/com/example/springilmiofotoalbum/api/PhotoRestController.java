package com.example.springilmiofotoalbum.api;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {
    @Autowired
    PhotoService photoService;

    @GetMapping
    public List<Photo> index (@RequestParam(name = "title")Optional<String> title){
        List<Photo> photos;
        if (title.isPresent()){
            photos = photoService.getFilteredPhoto(title.get());
        }else {
            photos = photoService.getAllPhotos();
        }
        return photos;
    }

    @GetMapping("/{id}")
    public Photo show(@PathVariable Integer id){
        try {
            return photoService.findPhoto(id);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public Photo store(@Valid @RequestBody Photo photo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return photoService.createPhoto(photo);
        }
    }

    @PutMapping("/{id}")
    public Photo update(@Valid @RequestBody Photo photo, @PathVariable Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            try {
                return photoService.updatePhoto(photo, id);
            } catch (RuntimeException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            photoService.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
