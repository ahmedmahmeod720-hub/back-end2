package com.backend.backend.repository;

import com.backend.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ريبوزيتوري الطلبات
 * JpaRepository يوفر تلقائياً: findAll(), findById(), save(), deleteById()
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    // كل العمليات الأساسية موجودة تلقائياً من JpaRepository
}
