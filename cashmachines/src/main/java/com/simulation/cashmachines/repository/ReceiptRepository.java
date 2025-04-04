package com.simulation.cashmachines.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simulation.cashmachines.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    
}