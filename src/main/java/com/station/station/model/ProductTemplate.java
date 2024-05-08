package com.station.station.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductTemplate {
    private  String productName;
    private Double unitPrice;
    private int eveporationRate;
}

