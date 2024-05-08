package com.station.station.service;

import com.station.station.Dbconnection.DatabaseConfing;
import com.station.station.model.Product;
import com.station.station.model.Station;
import com.station.station.model.StockMouvement;
import com.station.station.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ProductService implements ProductRepository {
    @Override
    public void saveProduct(int id, Product product, Station station, StockMouvement stockMouvement) throws SQLException {
        Statement statement = null;
        DatabaseConfing databaseConfing = new DatabaseConfing();
        Connection conn = databaseConfing.getConnection();

        try {
            FonctionServiceUse functionUse = new FonctionServiceUse();
            String timeNow = functionUse.readTimeNow(conn, "now()");
            int idProductTemplate = functionUse.readIdProductTemplate(conn, product.getProductTemplate().getProductName());
            System.out.println(product.getProductTemplate().getProductName());
            System.out.println(idProductTemplate);
            int idStation = functionUse.readIdStation(conn, station.getLocation());
            int idProduct = functionUse.readIdProduct(conn, idProductTemplate, idStation);
            System.out.println(idProduct);
            float quantityInStock = functionUse.readQuantityInStock(conn, idStation, idProduct);
            int idStock = functionUse.readIdStock(conn, idStation, idProduct);
            System.out.println(idStock);

            statement = conn.createStatement();

            String query1 = String.format("INSERT INTO supply (id_station, id_product, quantity, Supply_date) VALUES ('%s', '%s','%s', CURRENT_TIMESTAMP);",idStation,idProduct,stockMouvement.getQuantity());
            statement.executeUpdate(query1);
            System.out.println("Supply ok ✔ ");

            float newQuantity = stockMouvement.getQuantity() + quantityInStock;
            String query2 = String.format("UPDATE stock SET quantity_in_stock='%s', last_stock_update_date='%s' WHERE id_stock='%s';", newQuantity, timeNow, idStock);
            statement.executeUpdate(query2);
            System.out.println("Update quantity In stock and last stock update date ok ✔");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
