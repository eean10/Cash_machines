package com.simulation.cashmachines.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.simulation.cashmachines.dto.ProductSalesDTO;
import com.simulation.cashmachines.entity.Product;
import com.simulation.cashmachines.entity.Receipt;
import com.simulation.cashmachines.entity.ReceiptBodyItem;
import com.simulation.cashmachines.entity.Section;
import com.simulation.cashmachines.entity.StockItem;
import com.simulation.cashmachines.repository.ReceiptRepository;


@Service
public class ReceiptService {
    
    private final ReceiptRepository receiptRepository;
    private final StockItemService stockItemService;
    private final ProductService productService;


    public ReceiptService(ReceiptRepository receiptRepository, StockItemService stockItemService, ProductService productService) {
        this.receiptRepository = receiptRepository;
        this.stockItemService = stockItemService;
        this.productService = productService;
    }
    
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }


    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receipt not found"));
    }

    public Receipt createReceipt() {
        Receipt receipt = new Receipt();      
        return receiptRepository.save(receipt);
    }

    private ReceiptBodyItem buildReceiptBodyItem(String barcode, Integer quantity) {
        StockItem stockItem = stockItemService.getStockItemByBarcode(barcode);
        Product product = productService.getProductByBarcode(barcode);
        return new ReceiptBodyItem(barcode, product.getName(), quantity, stockItem.getSellingPrice());
    }

    //addOrRemove is true if we want to add an item, false if we want to remove an item
    public Receipt updateReceipt(Long id, String barcode, Integer quantity, Boolean addOrRemove) {
        if (receiptRepository.existsById(id)) {
            Receipt receipt = receiptRepository.findById(id).get();
            receipt.setId(id);
            if (addOrRemove) {
                stockItemService.updateAvailability(barcode, quantity);
                receipt.addItems(buildReceiptBodyItem(barcode, quantity));
            } else {
                //swapped lines: first you check if ypu can remove the items from the receipt 
                // (bought? how many?) then you update the stock
                //when adding you need to check the stock first
                receipt.removeItems(buildReceiptBodyItem(barcode, quantity));
                stockItemService.updateAvailability(barcode, -quantity);           
            } 
            return receiptRepository.save(receipt);
        }
        throw new RuntimeException("Receipt not found");
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    public Double getTakingsAmountByDay(LocalDate date) {
        return receiptRepository.findByTimestampBetween(date.atStartOfDay(), date.atStartOfDay().plusDays(1))
            .stream()
            .mapToDouble(Receipt::getTotal)
            .sum();
    }

    public ProductSalesDTO getProductTakingsAmountByDay(String barcode, LocalDate date) {
        return receiptRepository.findByTimestampBetween(date.atStartOfDay(), date.atStartOfDay().plusDays(1))
            .stream()
            .flatMap(receipt -> receipt.getBody().entrySet().stream()) //get all items of all receipts
            .filter(entry -> entry.getKey().equals(barcode)) //filter the item with the correct barcode
            .collect(Collectors.reducing(
                new ProductSalesDTO(barcode, 0, 0.0),
                entry -> new ProductSalesDTO(barcode, entry.getValue().getQuantity(), entry.getValue().getSubtotal()),
                (p1, p2) -> new ProductSalesDTO( barcode, p1.getQuantity() + p2.getQuantity(), p1.getTotal() + p2.getTotal())
            ));
    }

    public Double getSectionTakingsAmountByDay(Section section, LocalDate date) {
        return receiptRepository.findByTimestampBetween(date.atStartOfDay(), date.atStartOfDay().plusDays(1))
            .stream()
            .flatMap(receipt -> receipt.getBody().entrySet().stream()) //get all items of all receipts
            .filter(entry -> {
                Product product = productService.getProductByBarcode(entry.getKey());
                return product.getSection().equals(section);
            })
            .mapToDouble(entry -> entry.getValue().getSubtotal())
            .sum();
    }

    public Double getSectionTakingsAmountByYear(Section section, Integer year) {
        LocalDateTime startDate = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year + 1, 1, 1, 0, 0);
        return receiptRepository.findByTimestampBetween(startDate, endDate)
            .stream()
            .flatMap(receipt -> receipt.getBody().entrySet().stream()) //get all items of all receipts
            .filter(entry -> {
                Product product = productService.getProductByBarcode(entry.getKey());
                return product.getSection().equals(section);
            })
            .mapToDouble(entry -> entry.getValue().getSubtotal())
            .sum();
    }



}