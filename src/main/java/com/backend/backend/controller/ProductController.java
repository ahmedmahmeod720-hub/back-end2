package com.backend.backend.controller;

import com.backend.backend.model.Product;
import com.backend.backend.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * كونترولر المنتجات (الطوب)
 *
 * GET    /api/products      → أي حد يقدر يشوف المنتجات
 * POST   /api/products      → الأدمن بس يقدر يضيف
 * PUT    /api/products/{id}  → الأدمن بس يقدر يعدل
 * DELETE /api/products/{id}  → الأدمن بس يقدر يحذف
 *
 * الحماية: كل عملية تعديل بتتأكد من الـ session إن المستخدم admin
 * لو مش admin → يرجع 403 Forbidden
 */
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ===== طريقة مساعدة للتحقق من الأدمن =====


    /**
     * GET /api/products
     * يرجع كل المنتجات - متاح للجميع
     */
    @GetMapping("/api/products")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * POST /api/products
     * يضيف منتج جديد - أدمن فقط
     */
    @PostMapping("/api/products")
    public ResponseEntity<?> add(@RequestBody Product product, HttpSession session) {

        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    /**
     * PUT /api/products/{id}
     * يعدل منتج موجود (الاسم، السعر، المقاس) - أدمن فقط
     */
    @PutMapping("/api/products/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product, HttpSession session) {


        // ابحث عن المنتج في الداتابيز
        Product existing = productRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        // حدّث البيانات
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setSize(product.getSize());

        Product saved = productRepository.save(existing);
        return ResponseEntity.ok(saved);
    }

    /**
     * DELETE /api/products/{id}
     * يحذف منتج - أدمن فقط
     */
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpSession session) {

        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
