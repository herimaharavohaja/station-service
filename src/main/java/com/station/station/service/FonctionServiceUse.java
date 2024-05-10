package com.station.station.service;

import java.sql.*;

public class FonctionServiceUse {
    public int readIdProductTemplate(Connection conn, String nameProduct) throws SQLException {
        int id = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT id_product_temp FROM product_template WHERE name = ?")) {
            statement.setString(1, nameProduct);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id_product_temp");
                }
            }
        }
        return id;
    }

    public int readIdStation(Connection conn, String location) throws SQLException {
        int id = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT id_station FROM station WHERE location = ?")) {
            statement.setString(1, location);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id_station");
                }
            }
        }
        return id;
    }

    public int readIdProduct(Connection conn, int idProductTemp, int idStation) throws SQLException {
        int id = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT id_product FROM product WHERE id_product_template = ? AND id_station = ?")) {
            statement.setInt(1, idProductTemp);
            statement.setInt(2, idStation);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id_product");
                }
            }
        }
        return id;
    }

    public int readIdStock(Connection conn, int idStation, int idProduct) throws SQLException {
        int id = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT id_stock_movement FROM stock_movement WHERE id_station = ? AND id_product = ?")) {
            statement.setInt(1, idStation);
            statement.setInt(2, idProduct);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id_stock_movement");
                }
            }
        }
        return id;
    }

    public String readTimeNow(Connection conn) throws SQLException {
        String time = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT CURRENT_TIMESTAMP AS current_time")) {
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    time = rs.getString("current_time");
                }
            }
        }
        return time;
    }

    public float readQuantityInStock(Connection conn, int idStation, int idProduct) throws SQLException {
        float quantity = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT quantity FROM stock_movement WHERE id_station = ? AND id_product = ?")) {
            statement.setInt(1, idStation);
            statement.setInt(2, idProduct);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getFloat("quantity");
                }
            }
        }
        return quantity;
    }
}
