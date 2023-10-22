package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Type;
import com.example.annonce_microservice.Repositories.AnnonceCollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public  class ServiceAnnonceIMP implements ServiceAnnonce{

    @Autowired
    AnnonceCollaborationRepository annonceCollaborationRepository;

    public List<AnnonceCollaboration> getAllAnnonces() {
        return annonceCollaborationRepository.findAll();
    }

    public AnnonceCollaboration getAnnonceById(Long id) {
        return annonceCollaborationRepository.findById(id).orElse(null);
    }

    public AnnonceCollaboration createAnnonce(AnnonceCollaboration annonce) {
        return annonceCollaborationRepository.save(annonce);
    }

    public AnnonceCollaboration updateAnnonce(Long id, AnnonceCollaboration nouvelleAnnonce) {
        AnnonceCollaboration annonceExistante = annonceCollaborationRepository.findById(id).orElse(null);

        if (annonceExistante != null) {
            // Met à jour les champs nécessaires
            annonceExistante.setIduser(nouvelleAnnonce.getIduser());
            annonceExistante.setDescription(nouvelleAnnonce.getDescription());
            annonceExistante.setPrix(nouvelleAnnonce.getPrix());
            annonceExistante.setNbplace(nouvelleAnnonce.getNbplace());
            annonceExistante.setReserve(nouvelleAnnonce.getReserve());

            annonceExistante.setType(nouvelleAnnonce.getType());

            annonceExistante.setDateLimite(nouvelleAnnonce.getDateLimite());

            return annonceCollaborationRepository.save(annonceExistante);
        } else {
            return null; // L'annonce n'existe pas
        }
    }
    public void deleteAnnonce(Long id) {
        annonceCollaborationRepository.deleteById(id);
    }



    public List<AnnonceCollaboration> searchByType(Type type) {
        // Recherche les annonces qui correspondent à la compétence spécifiée
        return annonceCollaborationRepository.findByType(type);
    }



    public void deleteExpiredAnnouncements() {
        Date currentDate = new Date();
        List<AnnonceCollaboration> expiredAnnouncements = annonceCollaborationRepository.findByDateLimiteBefore(currentDate);

        for (AnnonceCollaboration annonce : expiredAnnouncements) {
            // You can perform additional actions before deletion if needed
            annonceCollaborationRepository.delete(annonce);
        }
    }

}
