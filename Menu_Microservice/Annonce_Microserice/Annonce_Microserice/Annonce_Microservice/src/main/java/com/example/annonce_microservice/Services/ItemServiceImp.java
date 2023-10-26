package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.Category;
import com.example.annonce_microservice.Entities.Item;
import com.example.annonce_microservice.Repositories.ItemRepository;
import com.example.annonce_microservice.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImp implements ItemService{
    @Autowired
    ItemRepository ItemRepository;
    CategoryRepository CategoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // afficher la liste des Items
    public List<Item> getAllItems() {
        return  ItemRepository.findAll();
    }


    // ajouter Item
    public Item addItem(Item item) {
        return ItemRepository.save(item);
    }

    // modifier Item
    public Item updateItem(int id, Item nouvelleItem) {
        Item ItemExistante = ItemRepository.findById(id).orElse(null);

        if (ItemExistante != null) {
            // Met à jour les champs nécessaires
            ItemExistante.setId(nouvelleItem.getId());
            ItemExistante.setName(nouvelleItem.getName());
            ItemExistante.setDescription(nouvelleItem.getDescription());

            return ItemRepository.save(ItemExistante);
        } else {
            return null; // L'Item n'existe pas
        }
    }

    // supprimer Item
    public void deleteItem(int id) {
        ItemRepository.deleteById(id);

    }



    // Recherche Avancée

    public List<Item> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);

        Predicate keywordPredicate = builder.or(
                builder.like(root.get("name"), "%" + keyword + "%"),
                builder.like(root.get("description"), "%" + keyword + "%")
        );
        Predicate pricePredicate = builder.between(root.get("price"), minPrice, maxPrice);

        query.where(builder.and(keywordPredicate, pricePredicate));

        return entityManager.createQuery(query).getResultList();
    }

    // Affficher les moyennes des prices pour chaque categorie
    public Map<String, Double> getAveragePricesByCategory() {
        List<Category> categories = CategoryRepository.findAll();
        Map<String, Double> result = new HashMap<>();

        for (Category category : categories) {
            List<Item> items = ItemRepository.findByCategory(category);
            double total = 0;
            int count = 0;
            for (Item item : items) {
                total += item.getPrice();
                count++;
            }
            double average = count > 0 ? total / count : 0;
            result.put(category.getName(), average);
        }

        return result;
    }



}
