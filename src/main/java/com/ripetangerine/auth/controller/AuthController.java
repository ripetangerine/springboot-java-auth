package com.ripetangerine.auth.controller;

import com.ripetangerine.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ripetangerine.auth.model.User;

@Controller
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/home") // 상태 확인
    public String home(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        if(user==null) return "redirect:/login";

        return "home";
    }

    @PostMapping("/signup")
    public String signup(String username, String password){
        authService.signup(username, password);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user = authService.login(username, password);
        if(user == null){
            return "ERROR; controller : login"; // 로그인 실패함
        }
        session.setAttribute("loginUser", user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); //세션 죽음
        return "redirect:/login";
    }
}
