package com.emergent.fanout.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private String customerCountry;
    private Double amount;
}
