package com.station.station.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StockMouvement {
    private int idStation;
    private int idProduct;
    private float quantity;
    private String DateSypply;
    private Type type;
}
