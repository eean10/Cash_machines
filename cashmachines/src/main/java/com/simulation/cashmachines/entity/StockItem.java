package com.simulation.cashmachines.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "stock_items")
public class StockItem{
    
    @Id
    @Column(name = "barcode")
    private String barcode; //foreign key to product
    private LocalDate  validFrom;
    private LocalDate validTo;
    private Double basePrice;
    private Double sellingPrice;
    private Integer availability;

    //getters and setters

    public String getBarcode() {
        return barcode;
    }   

    public LocalDate getValidFrom() {
        return validFrom;
    }         

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }   

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }   

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    } 

    public Double getSellingPrice() {
        return sellingPrice;
    }   

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }   

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }
}

