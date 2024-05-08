package com.station.station.repository;

import com.station.station.model.Product;
import com.station.station.model.Station;
import com.station.station.model.StockMovement;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface ProductRepository {
    void saveProduct(int id, Product product, Station station, StockMovement stockMouvement) throws SQLException;
}
