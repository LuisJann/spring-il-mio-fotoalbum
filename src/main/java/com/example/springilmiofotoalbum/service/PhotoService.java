package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public Photo createPhoto(Photo formPhoto){
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setVisible(formPhoto.isVisible());
        photoToPersist.setUrl(formPhoto.getUrl());

        return photoRepository.save(photoToPersist);
    }
}
