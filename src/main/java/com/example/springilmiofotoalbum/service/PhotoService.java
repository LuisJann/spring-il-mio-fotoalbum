package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Photo createPhoto(Photo formPhoto){
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setIsVisible(formPhoto.getIsVisible());
        photoToPersist.setUrl(formPhoto.getUrl());

        Set<Category> formCategories = getPhotoCategory(formPhoto);
        photoToPersist.setCategories(formCategories);
        return photoRepository.save(photoToPersist);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("title"));
    }

    public Photo getById(Integer id){
        Optional<Photo> result = photoRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else {
            throw new RuntimeException("Foto non trovata");
        }
    }

    public List<Photo> getFilteredPhoto(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Photo updatePhoto(Photo formPhoto, Integer id){
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getUrl());
        photoToUpdate.setIsVisible(formPhoto.getIsVisible());

        Set<Category> formCategories = getPhotoCategory(formPhoto);
        photoToUpdate.setCategories(formCategories);
        return photoRepository.save(photoToUpdate);
    }

    public boolean deleteById(Integer id){
        try{
            photoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Set<Category> getPhotoCategory(Photo formPhoto){
        Set<Category> formCategories = new HashSet<>();
        if (formPhoto.getCategories() != null){
            for (Category category:
                 formPhoto.getCategories()) {
                formCategories.add(categoryRepository.findById(category.getId()).orElseThrow());
            }
        }
        return formCategories;
    }



}
