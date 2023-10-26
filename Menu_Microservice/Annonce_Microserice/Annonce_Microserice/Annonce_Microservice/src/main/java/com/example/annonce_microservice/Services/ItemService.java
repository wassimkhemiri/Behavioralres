package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Item> getAllItems();

    Item addItem(Item item);


    Item updateItem(int id , Item nouvelleOrder);
    void deleteItem(int id);

    List<Item> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice);
    Map<String, Double> getAveragePricesByCategory();

}
