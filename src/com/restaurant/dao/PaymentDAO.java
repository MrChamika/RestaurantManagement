package com.restaurant.dao;

import com.restaurant.model.Payment;
import java.util.List;

public interface PaymentDAO {
    Payment findById(int id);
    List<Payment> findByOrder(int orderId);
    boolean save(Payment payment);
}
