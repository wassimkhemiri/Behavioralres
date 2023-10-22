package com.example.annonce_microservice.Repositories;

import com.example.annonce_microservice.Entities.AnnonceCollaboration;
import com.example.annonce_microservice.Enum.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnnonceCollaborationRepository extends JpaRepository<AnnonceCollaboration, Long> {

    List<AnnonceCollaboration> findByType(Type type);
    List<AnnonceCollaboration> findByDateLimiteBefore(Date currentDate);

}

