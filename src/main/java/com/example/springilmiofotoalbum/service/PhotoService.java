package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public Photo createPhoto(Photo formPhoto){
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setIsVisible(formPhoto.getIsVisible());
        photoToPersist.setUrl(formPhoto.getUrl());

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
}
