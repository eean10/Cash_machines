package com.simulation.cashmachines.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import com.simulation.cashmachines.entity.StockItem;
import com.simulation.cashmachines.repository.StockItemRepository;

@Service
public class StockItemService {

    private final StockItemRepository stockItemRepository;

    public StockItemService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    public List<StockItem> getAllStockItems() {
        return stockItemRepository.findAll();
    }


    public StockItem getStockItemByBarcode(String barcode) {
        return stockItemRepository.findById(barcode)
            .orElseThrow(() -> new RuntimeException("Stock item not found"));
    }

    public StockItem createStockItem(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    public StockItem updateStockItem(String barcode, StockItem stockItem) {
        if (stockItemRepository.existsById(barcode)) {
            stockItem.setBarcode(barcode);
            return stockItemRepository.save(stockItem);
        }
        throw new RuntimeException("Stock item not found");
    }

    public void deleteStockItem(String barcode) {   
        stockItemRepository.deleteById(barcode);
    }
 
    //replace quantity in stock
    public void updateStock(Map<String, Integer> items) {
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            StockItem stockItem = getStockItemByBarcode(entry.getKey());
            stockItem.setAvailability(entry.getValue());
            updateStockItem(entry.getKey(), stockItem);
        }
    }

    //subtract sold quantity (or add returned quantity)
    public void updateAvailability(String barcode, Integer quantity) {
        StockItem stockItem = getStockItemByBarcode(barcode);

        if (stockItem.getAvailability() - quantity > 0){
            stockItem.setAvailability(stockItem.getAvailability() - quantity);
            stockItemRepository.save(stockItem);
        }
        else{
            throw new RuntimeException("Quantity not available for item " + barcode);
        }
            
    }
    
    

}