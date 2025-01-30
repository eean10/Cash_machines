package com.simulation.cashmachines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simulation.cashmachines.entity.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    
}