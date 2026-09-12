package com.backend.backend.controller;

import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/api/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> body,
            HttpSession session) {

        String username = body.get("username");
        String password = body.get("password");

        User user = userRepository.findByUsername(username);

        Map<String, Object> response = new HashMap<>();

        if (user != null && user.getPassword().equals(password)) {

            // Save user information in session
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // Debug
            System.out.println("===== LOGIN =====");
            System.out.println("SESSION ID = " + session.getId());
            System.out.println("USER = " + user.getUsername());
            System.out.println("ROLE = " + user.getRole());
            System.out.println("=================");

            response.put("success", true);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());

        } else {

            response.put("success", false);
            response.put("message", "اسم المستخدم أو كلمة المرور غلط");
        }

        return response;
    }

    // =========================
    // CHECK SESSION
    // =========================
    @GetMapping("/api/check-session")
    public Map<String, Object> checkSession(HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        Object role = session.getAttribute("role");
        Object username = session.getAttribute("username");

        System.out.println("===== CHECK SESSION =====");
        System.out.println("SESSION ID = " + session.getId());
        System.out.println("USERNAME = " + username);
        System.out.println("ROLE = " + role);
        System.out.println("=========================");

        response.put("loggedIn", username != null);
        response.put("isAdmin", "admin".equalsIgnoreCase(String.valueOf(role)));
        response.put("username", username);
        response.put("role", role);

        return response;
    }

    // =========================
    // LOGOUT
    // =========================
    @PostMapping("/api/logout")
    public Map<String, Object> logout(HttpSession session) {

        System.out.println("===== LOGOUT =====");
        System.out.println("SESSION ID = " + session.getId());
        System.out.println("==================");

        session.invalidate();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);

        return response;
    }


}