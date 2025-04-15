package com.simulation.cashmachines.entity;

public class ReceiptBodyItem {
    private String barcode;
    private String name;
    private Double pricePerUnit = 0.0;
    private Double discount = 0.0;
    private Integer quantity = 0;
    private Double subtotal = 0.0;

    //needed to add because of controller -> add and remove
    public ReceiptBodyItem(){}

    public ReceiptBodyItem(String barcode, String name, Integer quantity, Double sellingPrice, Double discount) {
        this.barcode = barcode;
        this.name = name;
        this.pricePerUnit = sellingPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.subtotal = this.pricePerUnit * quantity - discount;
    }


    public ReceiptBodyItem(String barcode, String name, Integer quantity, Double sellingPrice) {
        this(barcode, name, quantity, sellingPrice, 0.0);
    }


    //getters and setters
    public String getBarcode() {
        return barcode;
    }


    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void applyDiscount(Double discount) {
        this.discount = discount;
        this.calculateSubtotal();
    }

    public void calculateSubtotal() {
        this.subtotal = this.pricePerUnit * this.quantity - this.discount;
    }
}
