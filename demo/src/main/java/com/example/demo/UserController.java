package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto requestDto){
        userService.signup(requestDto.getUsername(), requestDto.getPassword());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto requestDto){
        User user = userService.authenticate(requestDto.getUsername(), requestDto.getPassword());
        String token = jwtUtil.createToken(user.getUsername());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity.ok() 
                .headers(headers)
                .body("로그인 성공! 토큰이 헤더에 포함되어 있습니다.");
    }
}
