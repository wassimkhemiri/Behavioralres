package com.example.annonce_microservice.Controllers;

import com.example.annonce_microservice.Entities.Item;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.annonce_microservice.Services.ItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ItemController {
    ItemService ItemService;
    // afficher la liste des Items
    @GetMapping("/AllItems")
    @ResponseBody
    public List<Item> getAllItems(){
        return ItemService.getAllItems();
    }

    // ajouter Item
    @PostMapping("/addItem")
    @ResponseBody
    public void addItem (@RequestBody Item item) {

        ItemService.addItem(item);
    }

    // modifier Item
    @PutMapping("/updateItem")
    @ResponseBody
    public Item updateItem(@PathVariable int id,@RequestBody Item item){
        return ItemService.updateItem(id,item);
    }

    // supprimer Item
    @DeleteMapping("/deleteItem/{id}")
    @ResponseBody
    public void deleteItem(@PathVariable int id){
        ItemService.deleteItem(id);
    }



    // Recherche Avancée
    @GetMapping("/search")
    public List<Item> search(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
        return ItemService.search(keyword,minPrice,maxPrice);
    }

    // Affficher les moyennes des prices pour chaque categorie
    @GetMapping("/average-prices-by-category")
    public ResponseEntity<Map<String, Double>> getAveragePricesByCategory() {
        Map<String, Double> result = ItemService.getAveragePricesByCategory();
        return ResponseEntity.ok(result);
    }
}
