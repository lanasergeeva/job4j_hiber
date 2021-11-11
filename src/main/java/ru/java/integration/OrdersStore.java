package ru.java.integration;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersStore {
    private final BasicDataSource pool;

    public OrdersStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Order save(Order order) {
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "INSERT INTO orders(name, description, created) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, order.getName());
            pr.setString(2, order.getDescription());
            pr.setTimestamp(3, order.getCreated());
            pr.execute();
            ResultSet id = pr.getGeneratedKeys();
            if (id.next()) {
                order.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order getOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getTimestamp(4));
    }

    public Collection<Order> findAll() {
        List<Order> rsl = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT * FROM orders")) {
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    rsl.add(getOrder(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public boolean replace(int id, Order order) {
        boolean result = false;
        try (Connection con = pool.getConnection();
             PreparedStatement statement =
                     con.prepareStatement(
                             "update orders set name = ?, description = ?, created = ? where id = ?")) {
            statement.setString(1, order.getName());
            statement.setString(2, order.getDescription());
            statement.setTimestamp(3, order.getCreated());
            statement.setInt(4, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<Order> findByName(String name) {
        List<Order> rsl = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT * FROM orders where name like ?")) {
            pr.setString(1, name);
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    rsl.add(getOrder(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Order findById(int id) {
        Order rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                rsl = getOrder(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
