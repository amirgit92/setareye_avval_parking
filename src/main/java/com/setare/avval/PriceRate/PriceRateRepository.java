package com.setare.avval.PriceRate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRateRepository extends JpaRepository<PriceRate, Integer> {
    PriceRate findTopByOrderByIdDesc();
}
