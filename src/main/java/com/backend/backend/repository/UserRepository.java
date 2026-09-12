package com.backend.backend.repository;

import com.backend.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ريبوزيتوري المستخدمين
 * Spring ينشئ كل أوامر SQL تلقائياً (SELECT, INSERT, UPDATE, DELETE)
 * أضفنا findByUsername علشان نبحث بالاسم عند تسجيل الدخول
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring ينشئ SQL تلقائياً: SELECT * FROM users WHERE username = ?
    User findByUsername(String username);
}
