package com.example.annonce_microservice.Services;

import com.example.annonce_microservice.Entities.Sponsor;

import java.math.BigDecimal;
import java.util.List;

public interface SponsorService {
    public List<Sponsor> getAllSponsors();

    public Sponsor addSponsor(Sponsor sponsor);


    public Sponsor updateSponsor(Sponsor sponsor);
    public void deleteSponsor(int id);
    public List<Sponsor> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice);
}