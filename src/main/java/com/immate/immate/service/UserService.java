package com.immate.immate.service;

import com.immate.immate.dto.SignupRequest;
import com.immate.immate.dto.UserInfo;
import com.immate.immate.entity.user.User;
import com.immate.immate.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 가입된 이메일입니다");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .investmentStyle(request.getInvestmentStyle())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserInfo getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));

        return convertToUserInfo(user);
    }

    private UserInfo convertToUserInfo(User user) {
        return new UserInfo(
            user.getEmail(),
            user.getName(),
            user.getInvestmentStyle()
        );
    }
}
