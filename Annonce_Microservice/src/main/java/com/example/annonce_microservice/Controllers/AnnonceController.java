package com.example.annonce_microservice.Controllers;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Type;
import com.example.annonce_microservice.Services.ServiceAnnonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonces")

public class AnnonceController {

    @Autowired
    public ServiceAnnonce annonceService;



    @GetMapping("/getAllAnnonces")
    public List<AnnonceCollaboration> getAllAnnonces() {
        return annonceService.getAllAnnonces();
    }

    @GetMapping("/getAnnoncesById/{id}")
    public AnnonceCollaboration getAnnonceById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id);
    }

    @PostMapping("/creatAnnonce")
    public AnnonceCollaboration createAnnonce(@RequestBody AnnonceCollaboration annonce) {
        return annonceService.createAnnonce(annonce);
    }

    @PutMapping("/updateAnnonce/{id}")
    public AnnonceCollaboration updateAnnonce(@PathVariable Long id, @RequestBody AnnonceCollaboration nouvelleAnnonce) {
        return annonceService.updateAnnonce(id, nouvelleAnnonce);
    }


    @DeleteMapping("/DeleteAnnonce/{id}")
    public void deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
    }

    @GetMapping("/searchAnnonce/types/{type}")
    public List<AnnonceCollaboration> searchByType(@PathVariable Type type) {
        return annonceService.searchByType(type);

}

    @Scheduled(cron = "0 0 0 * * *")
    //@Scheduled(cron = "0/1 * * * * *")
    public void deleteExpiredAnnouncements() {
        annonceService.deleteExpiredAnnouncements();
    }

}
