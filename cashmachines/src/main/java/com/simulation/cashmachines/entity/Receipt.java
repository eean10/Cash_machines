package com.simulation.cashmachines.entity;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "receipts")
public class Receipt{

    @Transient
    private final String header = "Find the right header - shop";

    @Id
    private Long id;
    private ReceiptBody body;
    private Double total;
    private LocalDateTime timestamp;

    @Transient
    private final String footer = "Find the right footer - shop";

    //getters and setters
    public Long getId() {
        return id;
    }

    //todo: back to 1 every day
    //todo: check also in db has the combination of id and timestamp unique
    public void setId(Long id) {
        this.id = id;
    }

    public ReceiptBody getBody() {
        return body;
    }

    public void setBody(ReceiptBody body) {
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

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }



}