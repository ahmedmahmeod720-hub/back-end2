package com.backend.backend.controller;

import com.backend.backend.model.Order;
import com.backend.backend.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * كونترولر الطلبات
 *
 * POST   /api/orders       → أي حد يقدر يبعت طلب
 * GET    /api/orders       → الأدمن بس يقدر يشوف الطلبات
 * DELETE /api/orders/{id}  → الأدمن بس يقدر يحذف طلب
 */
@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * التأكد إن المستخدم أدمن
     */
    private boolean isAdmin(HttpSession session) {
        Object role = session.getAttribute("role");
        return "admin".equalsIgnoreCase(String.valueOf(role));
    }

    /**
     * POST /api/orders
     * يحفظ طلب جديد - متاح لأي عميل
     */
    @PostMapping("/api/orders")
    public ResponseEntity<?> add(@RequestBody Order order) {

        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(saved);
    }

    /**
     * GET /api/orders
     * يرجع كل الطلبات - أدمن فقط
     */
    @GetMapping("/api/orders")
    public ResponseEntity<?> getAll(HttpSession session) {

        if (!isAdmin(session)) {
            return ResponseEntity.status(403).body(
                    Map.of(
                            "success", false,
                            "message", "غير مسموح لك بعرض الطلبات"
                    )
            );
        }

        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    /**
     * DELETE /api/orders/{id}
     * يحذف طلب - أدمن فقط
     */
    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            HttpSession session) {

        if (!isAdmin(session)) {
            return ResponseEntity.status(403).body(
                    Map.of(
                            "success", false,
                            "message", "غير مسموح لك بحذف الطلب"
                    )
            );
        }

        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
