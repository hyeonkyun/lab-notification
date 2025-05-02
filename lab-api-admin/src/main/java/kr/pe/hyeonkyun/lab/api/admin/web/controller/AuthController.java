package kr.pe.hyeonkyun.lab.api.admin.web.controller;


import jakarta.validation.Valid;
import kr.pe.hyeonkyun.lab.api.admin.common.utils.JwtUtil;
import kr.pe.hyeonkyun.lab.api.admin.domain.model.Account;
import kr.pe.hyeonkyun.lab.api.admin.domain.repository.AccountRepository;
import kr.pe.hyeonkyun.lab.api.admin.web.dto.AuthRequest;
import kr.pe.hyeonkyun.lab.api.admin.web.dto.AuthResponse;
import kr.pe.hyeonkyun.lab.api.admin.web.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/lab-admin/api")
@RequiredArgsConstructor
public class AuthController {
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/v1/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest req) {
        if (accountRepository.findByUserId(req.getUserId()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 사용자명입니다.");
        }
        Account newUser = new Account(req.getUserId(), passwordEncoder.encode(req.getPassword()), "1", req.getReqReason(), req.getUserId(), LocalDateTime.now(), req.getUserId(), LocalDateTime.now() );
        accountRepository.save(newUser);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/v1/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        return accountRepository.findByUserId(req.getUserId())
                .filter(user -> passwordEncoder.matches(req.getPassword(), user.getPassword()))
                .map(user -> {
                    String access = jwtUtil.generateToken(user.getUserId(), 60 * 60 * 1000);
                    String refresh = jwtUtil.generateToken(user.getUserId(), 24 * 60 * 60 * 1000);
                    return ResponseEntity.ok(new AuthResponse(access, refresh));
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 실패"));
    }

    @PostMapping("/v1/auth/refresh")
    public ResponseEntity<?> refresh(@RequestBody TokenRequest req) {
        if (jwtUtil.validateToken(req.getRefreshToken())) {
            String username = jwtUtil.extractUsername(req.getRefreshToken());
            String newAccessToken = jwtUtil.generateToken(username, 15 * 60 * 1000);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh 토큰 만료");
    }

    @GetMapping("/v1/auth/me")
    public ResponseEntity<?> me(Authentication authentication) {
        return ResponseEntity.ok("안녕하세요, " + authentication.getName() + "님!");
    }
}