package ru.home.dao_layer.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.home.dao_layer.entities.Order;
import ru.home.dao_layer.entities.OrderMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository implements OrderRepositoryInterface {
    private final String scriptFileName = "script.sql";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OrderMapper orderMapper;

    public OrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, OrderMapper orderMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderMapper = orderMapper;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getProductName(String name) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        List<String> orders = namedParameterJdbcTemplate.query(
                        OrderRepository.read(scriptFileName),
                        params,
                        orderMapper
                ).stream()
                .map(Order::getProductName)
                .toList();

        return orders.isEmpty() ? "Not found" : String.join(", ", orders);
    }
}
