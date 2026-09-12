package com.backend.backend.repository;

import com.backend.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ريبوزيتوري المنتجات
 * JpaRepository يوفر تلقائياً: findAll(), findById(), save(), deleteById()
 * مش محتاجين نكتب أي SQL
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    // كل العمليات الأساسية موجودة تلقائياً من JpaRepository
}
