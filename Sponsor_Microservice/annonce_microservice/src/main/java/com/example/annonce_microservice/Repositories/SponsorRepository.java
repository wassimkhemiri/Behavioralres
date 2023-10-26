package com.example.annonce_microservice.Repositories;

import com.example.annonce_microservice.Entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository  extends JpaRepository<Sponsor,Integer> {
}