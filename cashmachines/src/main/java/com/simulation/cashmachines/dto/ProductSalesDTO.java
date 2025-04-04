package com.simulation.cashmachines.dto;

public class ProductSalesDTO {

    private String barcode;
    private Integer quantity;
    private Double total;

    public ProductSalesDTO(String barcode, Integer quantity, Double total) {
        this.barcode = barcode;
        this.quantity = quantity;
        this.total = total;
    }   

    public String getBarcode() {
        return barcode;
    }

    public Integer getQuantity() {
        return quantity;
    }       

    public Double getTotal() {
        return total;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }   

    public void setTotal(Double total) {
        this.total = total;
    }   
}
