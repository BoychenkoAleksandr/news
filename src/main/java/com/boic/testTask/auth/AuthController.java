package com.boic.test_task.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
//    private final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//        return ResponseEntity.ok(authService.authenticate(request));
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody User user) {
//        return ResponseEntity.ok(authService.register(user));
//    }
}
