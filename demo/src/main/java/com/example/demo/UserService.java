package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화 도구
    }

    public void signup(String username, String password){
        if(userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, encodedPassword));
    }

    public User authenticate(String username, String password){
        User user = userRepository.findByUsername(username)
                    .orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
        
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
