package com.setare.avval.repository;

import com.setare.avval.model.PriceRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRateRepository extends JpaRepository<PriceRate, Integer> {
    PriceRate findTopByOrderByIdDesc();
}
