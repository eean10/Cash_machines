package com.simulation.cashmachines.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

import com.simulation.cashmachines.dto.ProductSalesDTO;
import com.simulation.cashmachines.entity.Receipt;
import com.simulation.cashmachines.entity.Section;
import com.simulation.cashmachines.service.ReceiptService;

@RestController
public class ReceiptController{

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/receipts")
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/receipts/{id}")
    public Receipt getReceiptById(@PathVariable Long id) {
        System.out.println("Receipt id: " + id);
        System.out.println("Receipt: ");
        Receipt receipt = receiptService.getReceiptById(id);
        System.out.println(receipt.toString());
        return receipt;
    }

    @PostMapping("/receipts") 
    public Receipt createReceipt() {
        Receipt receipt = receiptService.createReceipt();
        System.out.println("Receipt created: ");
        System.out.println(receipt.toString());
        return receipt;
    }

    @PutMapping("/receipts/{id}/add_item/{barcode}")
    public Receipt updateReceiptAddItem(@PathVariable Long id, @RequestBody String barcode, @RequestBody Integer quantity) {
        System.out.println("Receipt id: " + id);
        System.out.println("Receipt: ");
        Receipt receipt = receiptService.updateReceipt(id, barcode, quantity, true);
        System.out.println(receipt.toString());
        return receipt;
    }

    @PutMapping("/receipts/{id}/remove_item/{barcode}")
    public Receipt updateReceiptRemoveItem(@PathVariable Long id, @RequestBody String barcode, @RequestBody Integer quantity) {
        System.out.println("Receipt id: " + id);
        System.out.println("Receipt: ");
        Receipt receipt = receiptService.updateReceipt(id, barcode, quantity, true);
        System.out.println(receipt.toString());
        return receipt;
    }

    @DeleteMapping("/receipts/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }

    @GetMapping("/receipts/takings_amount_by_day/{date}")
    public Double getTakingsAmountByDay(@PathVariable LocalDate date) {
        return receiptService.getTakingsAmountByDay(date);
    }

    @GetMapping("/receipts/product_takings_amount_by_day/{barcode}/{date}")
    public ProductSalesDTO getProductTakingsAmountByDay(@PathVariable String barcode, @PathVariable LocalDate date) {
        return receiptService.getProductTakingsAmountByDay(barcode, date);
    }
    @GetMapping("/receipts/section_takings_amount_by_day/{section}/{date}")
    public Double getSectionTakingsAmountByDay(@PathVariable Section section, @PathVariable LocalDate date) {
        return receiptService.getSectionTakingsAmountByDay(section, date);
    }

    @GetMapping("/receipts/section_takings_amount_by_year/{section}/{year}")
    public Double getSectionTakingsAmountByDay(@PathVariable Section section, @PathVariable Integer year) {
        return receiptService.getSectionTakingsAmountByYear(section, year);
    }
}