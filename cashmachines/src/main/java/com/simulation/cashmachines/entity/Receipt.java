package com.simulation.cashmachines.entity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "receipts")
public class Receipt{

    @Transient
    private final String header = "Find the right header - shop";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //todo : capire perché non gli piace sta cosa

    @Type(JsonType.class)
    @Column( columnDefinition = "jsonb")
    private Map<String, ReceiptBodyItem> body;
    
    private Double total = 0.0;
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Transient
    private final String footer = "Find the right footer - shop";

    @PrePersist
    public void prePersist() {
        if (body == null) {
            body = new HashMap<>();  // Se body è null, inizializzalo con una mappa vuota
        }
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, ReceiptBodyItem> getBody() {
        return body;
    }

    public void setBody(Map<String, ReceiptBodyItem> body) {
        this.body = body;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {

        return header + "\n" + id + "\n" + bodyToString() + "\n" + total + "\n" + footer;
    }    

    private String bodyToString() {
        if (body == null) return "";

        return body.values().stream()
            .map(item -> item.getName() + "\t" + item.getSubtotal() + "€\n" +
             item.getQuantity() + "x\t" + item.getPricePerUnit() + "€")
            .collect(Collectors.joining("\n"));
    }

    public void addItems(ReceiptBodyItem receiptBodyItem) {
        String barcode = receiptBodyItem.getBarcode();
        if (body.get(barcode) != null) {
            Integer currentQuantity = body.get(barcode).getQuantity();
            Integer newQuantity = currentQuantity + receiptBodyItem.getQuantity();
            body.get(barcode).setQuantity(newQuantity); 
        }
        else {
            body.put(barcode, receiptBodyItem); 
        }
    }

    public void removeItems(ReceiptBodyItem receiptBodyItem) {
        String barcode = receiptBodyItem.getBarcode();
        if (body.get(barcode) != null) {
            Integer currentQuantity = body.get(barcode).getQuantity();
            Integer newQuantity = receiptBodyItem.getQuantity();
            if (currentQuantity >= newQuantity){
               body.get(barcode).setQuantity(newQuantity); 
            }
            else {
                throw new RuntimeException("Not possible: you can remove " + currentQuantity + " item(s) at most");
            }           
        }
        else {
            throw new RuntimeException("Item not found");
        }
    }


}