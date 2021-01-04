package com.example.lab10.repository;

import com.example.lab10.model.Order;
import com.example.lab10.model.OrderItem;
import com.example.lab10.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(OrderItem item, int orderId) {
        String sql = "INSERT INTO order_items VALUES(NULL, ?, ?, ?)";

        jdbcTemplate.update(sql,
                item.getProduct().getId(),
                orderId,
                item.getQuantity());
    }

    public List<OrderItem> getItemOrderByOrderId(Integer id) {
        String sql = "select * from order_items oi, products p where oi.productid=p.id and orderid= ?";
        List<Map<String, Object>> orderItems = jdbcTemplate.queryForList(sql,id);
        List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
        orderItems.forEach(item ->{
            OrderItem orderItem = new OrderItem();
            orderItem.setId((Integer) item.get("id"));
            Product product = new Product();
            product.setId((Integer) item.get("productId"));
            product.setName((String) item.get("name"));
            product.setPrice((double)item.get("price"));
            product.setAvailableStock((Integer)item.get("available_stock"));
            orderItem.setProduct(product);
            orderItem.setQuantity((Integer)item.get("quantity"));
            orderItemsList.add(orderItem);
        });
        return orderItemsList;
    }
}
