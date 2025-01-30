package com.simulation.cashmachines.entity;

import java.util.Map;

public class ReceiptBody {
    private Map<String, ReceiptBodyItem> items;

    //getters and setters

    public Map<String, ReceiptBodyItem> getItems() {
        return items;
    }

    public void setItems(Map<String, ReceiptBodyItem> items) {
        this.items = items;
    }
}
