package com.station.station.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@Service
public class FonctionServiceUse {
    public int readIdProductTemplate(Connection conn, String nameProduct) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format(" select id_product_temp from product_template where name='%s' ", nameProduct);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_product_temp");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int readIdStation(Connection conn, String location) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format("select id_station from station where location='%s'", location);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_station");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int readIdProduct(Connection conn, int idProductTemp, int idStation) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format("select id_product from product where id_product_temp='%s' and  id_station='%s'", idProductTemp, idStation);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_product");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int readIdStock(Connection conn, int idStation, int idProduct) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format("select  id_stock from stock where id_station='%s' and id_product='%s'",idStation,idProduct);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_stock");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String readTimeNow(Connection conn, String now) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select(%s)", now);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                h = rs.getString("now");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return h;

    }

    public float readQuantityInStock(Connection conn, int idStation, int idProduct) {
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format("select  quantity_in_stock from stock where id_station='%s' and id_product='%s' ",idStation,idProduct);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("quantity_in_stock");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }

}



