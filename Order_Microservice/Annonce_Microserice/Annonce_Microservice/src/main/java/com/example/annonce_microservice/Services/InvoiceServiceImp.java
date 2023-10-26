package com.example.annonce_microservice.Services;


import com.example.annonce_microservice.Entities.Invoice;
import com.example.annonce_microservice.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceServiceImp implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return  invoiceRepository.findAll();
    }


    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(int id, Invoice nouvelleInvoice) {
        Invoice InvoiceExistante = invoiceRepository.findById(id).orElse(null);

        if (InvoiceExistante != null) {
            // Met à jour les champs nécessaires
            InvoiceExistante.setId(nouvelleInvoice.getId());
            InvoiceExistante.setTotalAmount(nouvelleInvoice.getTotalAmount());


            return invoiceRepository.save(InvoiceExistante);
        } else {
            return null; // L'annonce n'existe pas
        }
    }



    public void deleteInvoice(int id) {
        invoiceRepository.deleteById(id);

    }
}
