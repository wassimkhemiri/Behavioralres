package com.example.annonce_microservice.Controllers;

import com.example.annonce_microservice.Entities.Invoice;
import com.example.annonce_microservice.Services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class InvoiceController {
    InvoiceService piService;

    // Afficher invoices
    @GetMapping("/AllInvoices")
    @ResponseBody
    public List<Invoice> getAllInvoice(){
        return piService.getAllInvoices();
    }

    // Ajouter invoice
    @PostMapping("/addInvoices")
    @ResponseBody
    public Invoice addInvoice (@RequestBody Invoice invoice) {

        return piService.addInvoice(invoice);
    }

    // Modifier invoice
    @PutMapping("/updateInvoice")
    @ResponseBody
    public Invoice updateInvoice(@PathVariable int id,@RequestBody Invoice invoice){
        return piService.updateInvoice(id,invoice);
    }
    // Supprimer invoice
    @DeleteMapping("/deleteInvoice/{id}")
    @ResponseBody
    public void deleteInvoice(@PathVariable int id){

        piService.deleteInvoice(id);
    }
}
