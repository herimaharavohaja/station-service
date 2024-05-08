package com.station.station.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    Station station;
    ProductTemplate productTemplate;
}

