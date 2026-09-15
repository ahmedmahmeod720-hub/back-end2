package com.backend.backend.controller;

import com.backend.backend.model.Governorate;
import com.backend.backend.repository.GovernorateRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * كونترولر المحافظات
 *
 * GET    /api/governorates       → أي حد يقدر يشوف المحافظات
 * POST   /api/governorates       → الأدمن بس يقدر يضيف
 * PUT    /api/governorates/{id}  → الأدمن بس يقدر يعدل
 * DELETE /api/governorates/{id}  → الأدمن بس يقدر يحذف
 */
@RestController
public class GovernorateController {

    private final GovernorateRepository governorateRepository;

    public GovernorateController(GovernorateRepository governorateRepository) {
        this.governorateRepository = governorateRepository;
    }

    /**
     * التأكد إن المستخدم أدمن
     */
    private boolean isAdmin(HttpSession session) {
        Object role = session.getAttribute("role");
        return "admin".equalsIgnoreCase(String.valueOf(role));
    }

    /**
     * GET /api/governorates
     * يرجع كل المحافظات - متاح للجميع
     */
    @GetMapping("/api/governorates")
    public List<Governorate> getAll() {
        return governorateRepository.findAll();
    }

    /**
     * POST /api/governorates
     * يضيف محافظة جديدة - أدمن فقط
     */
    @PostMapping("/api/governorates")
    public ResponseEntity<?> add(
            @RequestBody Governorate governorate,
            HttpSession session) {

        if (!isAdmin(session)) {
            return ResponseEntity.status(403).body(
                    Map.of(
                            "success", false,
                            "message", "غير مسموح لك بإضافة محافظة"
                    )
            );
        }

        Governorate saved = governorateRepository.save(governorate);
        return ResponseEntity.ok(saved);
    }

    /**
     * PUT /api/governorates/{id}
     * يعدل المحافظة والـ freight - أدمن فقط
     */
    @PutMapping("/api/governorates/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Governorate governorate,
            HttpSession session) {

        if (!isAdmin(session)) {
            return ResponseEntity.status(403).body(
                    Map.of(
                            "success", false,
                            "message", "غير مسموح لك بتعديل المحافظة"
                    )
            );
        }

        Governorate existing =
                governorateRepository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(governorate.getName());
        existing.setFreight(governorate.getFreight());

        Governorate saved = governorateRepository.save(existing);
        return ResponseEntity.ok(saved);
    }

    /**
     * DELETE /api/governorates/{id}
     * يحذف محافظة - أدمن فقط
     */
    @DeleteMapping("/api/governorates/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            HttpSession session) {

        if (!isAdmin(session)) {
            return ResponseEntity.status(403).body(
                    Map.of(
                            "success", false,
                            "message", "غير مسموح لك بحذف المحافظة"
                    )
            );
        }

        governorateRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
