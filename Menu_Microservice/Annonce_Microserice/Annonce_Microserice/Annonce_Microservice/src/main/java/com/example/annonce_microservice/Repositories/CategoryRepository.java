package com.example.annonce_microservice.Repositories;


import com.example.annonce_microservice.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
