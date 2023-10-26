package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.Sponsor;
import com.example.annonce_microservice.Repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Service
public class SponsorServiceImp implements SponsorService{
    @Autowired
    SponsorRepository sponsorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }


    public Sponsor addSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }


    public Sponsor updateSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }


    public void deleteSponsor(int id) {
        sponsorRepository.deleteById(id);
    }



    public List<Sponsor> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sponsor> query = builder.createQuery(Sponsor.class);
        Root<Sponsor> root = query.from(Sponsor.class);

        Predicate keywordPredicate = builder.or(
                builder.like(root.get("name"), "%" + keyword + "%"),
                builder.like(root.get("email"), "%" + keyword + "%")
        );
        Predicate pricePredicate = builder.between(root.get("amount"), minPrice, maxPrice);

        query.where(builder.and(keywordPredicate, pricePredicate));

        return entityManager.createQuery(query).getResultList();
    }
}