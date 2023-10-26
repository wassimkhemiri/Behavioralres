package com.example.annonce_microservice.Controllers;

import com.example.annonce_microservice.Entities.Sponsor;
import com.example.annonce_microservice.Services.SponsorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController

@AllArgsConstructor
public class SponsorController {
    SponsorService sponsorService;

    // Afficher la liste des categories
    @GetMapping("/AllSponsor")
    @ResponseBody
    public List<Sponsor> getAllSponsor(){
        return sponsorService.getAllSponsors();
    }

    // Ajouter une categorie
    @PostMapping("/addSponsor")
    @ResponseBody
    public void addSponsor (@RequestBody Sponsor Sponsor) {

        sponsorService.addSponsor(Sponsor);


    }

    // Modifier une categorie
    @PutMapping("/updateSponsor")
    @ResponseBody
    public Sponsor updateSponsor(@RequestBody Sponsor  Sponsor){
        return sponsorService.updateSponsor(Sponsor);
    }

    // Supprimer une categorie
    @DeleteMapping("/deleteSponsor/{id}")
    @ResponseBody
    public void deleteCategory(@PathVariable int id){

        sponsorService.deleteSponsor(id);
    }

    // Recherche avancée avec trois paramétres
    @GetMapping("/search")
    public List<Sponsor> search(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
        return sponsorService.search(keyword,minPrice,maxPrice);
    }

}