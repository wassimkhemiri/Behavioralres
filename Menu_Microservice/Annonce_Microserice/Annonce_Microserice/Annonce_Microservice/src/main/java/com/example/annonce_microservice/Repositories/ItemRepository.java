package com.example.annonce_microservice.Repositories;


import com.example.annonce_microservice.Entities.Category;
import com.example.annonce_microservice.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByCategory(Category category);

}
