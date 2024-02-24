package ru.home.dao_layer.entities;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getDate("date"),
                rs.getInt("customer_id"),
                rs.getString("product_name"),
                rs.getInt("amount")
        );
    }
}
