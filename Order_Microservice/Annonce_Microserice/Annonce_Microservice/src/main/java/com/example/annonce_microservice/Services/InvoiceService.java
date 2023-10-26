package com.example.annonce_microservice.Services;

import com.example.annonce_microservice.Entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();

    Invoice addInvoice(Invoice invoice);


    Invoice updateInvoice(int id,Invoice ouvelleInvoice);
    void deleteInvoice(int id);
}
