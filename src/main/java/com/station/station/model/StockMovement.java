package com.station.station.model;

import com.station.station.model.enums.Type;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StockMovement {
    private int id_supply;
    private Station station;
    private Product product;
    private BigDecimal quantity;
    private Timestamp date_stock;
    private Type type;
}
