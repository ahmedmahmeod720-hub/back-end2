package com.backend.backend.repository;

import com.backend.backend.model.Governorate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ريبوزيتوري المحافظات
 * JpaRepository يوفر تلقائياً: findAll(), findById(), save(), deleteById()
 */
public interface GovernorateRepository extends JpaRepository<Governorate, Long> {
    // كل العمليات الأساسية موجودة تلقائياً من JpaRepository
}
