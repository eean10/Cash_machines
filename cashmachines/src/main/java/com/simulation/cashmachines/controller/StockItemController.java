package com.simulation.cashmachines.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.simulation.cashmachines.entity.StockItem;
import com.simulation.cashmachines.service.StockItemService;


@RestController
public class StockItemController{

    private final StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @GetMapping("/stock")
    public List<StockItem> getAllStockItems() {
        return stockItemService.getAllStockItems();
    }


    @GetMapping("/stock/{barcode}")
    public StockItem getStockItemByBarcode(@PathVariable String barcode) {
        return stockItemService.getStockItemByBarcode(barcode);
    }

    @PostMapping("/stock")
    public StockItem createStockItem(@RequestBody StockItem stockItem) {
        return stockItemService.createStockItem(stockItem);
    }

    @PutMapping("/stock/{barcode}")
    public StockItem updateStockItem(@PathVariable String barcode, @RequestBody StockItem stockItem) {
        return stockItemService.updateStockItem(barcode, stockItem);
    }

    @DeleteMapping("/stock/{barcode}")
    public void deleteStockItem(@PathVariable String barcode) {
        stockItemService.deleteStockItem(barcode);
    }

}