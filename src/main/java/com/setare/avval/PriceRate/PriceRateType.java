package com.setare.avval.PriceRate;

import lombok.Getter;

@Getter
public enum PriceRateType {
    hourly(5000),
    daily(100000),
    monthly(500000);
    private int rate;
    PriceRateType(int rate) {
        this.rate = rate;
    }
}
