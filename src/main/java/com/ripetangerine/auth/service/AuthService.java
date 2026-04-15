package com.ripetangerine.auth.service;

import com.ripetangerine.auth.model.User;
import com.ripetangerine.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void signup(String username, String password){ // 회원가입 : 중복 체크, 유저 생성, 저장
        if(userRepository.findByUsername(username).isEmpty()){
            throw new RuntimeException("존재하는 유저");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);
    }

    public User login(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("ERROR : 존재하지 않는 유저"));

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("ERROR : 비번 틀려먹음");
        }
        return user;
    }
}
