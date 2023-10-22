package com.example.annonce_microservice.Services;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Type;

import java.util.List;

public interface ServiceAnnonce {
    List<AnnonceCollaboration> getAllAnnonces();
    AnnonceCollaboration getAnnonceById(Long id);
    AnnonceCollaboration createAnnonce(AnnonceCollaboration annonce);

    AnnonceCollaboration updateAnnonce(Long id, AnnonceCollaboration nouvelleAnnonce);

    void deleteAnnonce(Long id);

    void deleteExpiredAnnouncements();

    List<AnnonceCollaboration> searchByType(Type type);
}
