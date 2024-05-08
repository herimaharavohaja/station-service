package com.station.station.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vente {
    private int idVente;
    private Station station;
    private Product product;
    private BigDecimal quantityPrice;
    private Timestamp dateVente;
}
