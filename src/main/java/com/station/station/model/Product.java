package com.station.station.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private int idProduct;
    private Station station;
    private ProductTemplate productTemplate;
}

