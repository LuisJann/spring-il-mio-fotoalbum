package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category create(Category formCategory){
        Category categoryToCreate = new Category();
        categoryToCreate.setTitle(formCategory.getTitle());

        return categoryRepository.save(categoryToCreate);
    }

    public Category getById(Integer id){
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    public Category update(Category formCategory){
        return categoryRepository.save(formCategory);
    }

    public boolean deleteById(Integer id) {
        getById(id);
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
