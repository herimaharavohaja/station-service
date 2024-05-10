package com.station.station.service;

import com.station.station.Dbconnection.DatabaseConfing;
import com.station.station.model.Product;
import com.station.station.model.Station;
import com.station.station.model.StockMovement;
import com.station.station.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ProductService implements ProductRepository {
    private final StockMovement stockMovement;

    public ProductService(StockMovement stockMovement) {
        this.stockMovement = stockMovement;
    }

    @Override
    public void saveProduct(int id, Product product, Station station, StockMovement stockMovement) throws SQLException {
        DatabaseConfing databaseConfig = new DatabaseConfing();
        Connection conn = null;
        PreparedStatement supplyStatement = null;
        PreparedStatement updateStatement = null;

        try {
            conn = databaseConfig.getConnection();
            conn.setAutoCommit(false);

            FonctionServiceUse functionUse = new FonctionServiceUse();
            String timeNow = functionUse.readTimeNow(conn);
            int idProductTemplate = functionUse.readIdProductTemplate(conn, product.getProductTemplate().getProductName());
            int idStation = functionUse.readIdStation(conn, station.getLocation());
            int idProduct = functionUse.readIdProduct(conn, idProductTemplate, idStation);
            BigDecimal quantityInStock = BigDecimal.valueOf(functionUse.readQuantityInStock(conn, idStation, idProduct));
            int idStock = functionUse.readIdStock(conn, idStation, idProduct);

            String supplyQuery = "INSERT INTO stock_movement (id_station, id_product, quantity, date_stock) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            String updateQuery = "UPDATE stock_movement SET quantity = ?, date_stock = ? WHERE id_stock_movement = ?";

            supplyStatement = conn.prepareStatement(supplyQuery);
            supplyStatement.setInt(1, idStation);
            supplyStatement.setInt(2, idProduct);
            supplyStatement.setBigDecimal(3, stockMovement.getQuantity());
            supplyStatement.executeUpdate();

            BigDecimal newQuantity = stockMovement.getQuantity().add(quantityInStock);

            updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setBigDecimal(1, newQuantity);
            updateStatement.setString(2, timeNow);
            updateStatement.setInt(3, idStock);
            updateStatement.executeUpdate();

            conn.commit();

            System.out.println("Stock movement and stock update successful ✔");
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            if (supplyStatement != null) {
                supplyStatement.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
