package com.example.demo.user.service;

import com.example.demo.security.jwt.JwtTokenProvider;
import com.example.demo.user.dto.*;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final KaKaoApiService kaKaoApiService;

    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException();
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.createUser(request, encodedPassword);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(IllegalArgumentException::new);

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException();
        }

        return createLoginResponse(user);
    }

    @Transactional
    public LoginResponse kakaoLogin(KakaoLoginRequest request) {
        KakaoTokenResponse kakaoToken = kaKaoApiService.getAccessToken(request.getCode());
        KakaoUserInfoResponse userInfo = kaKaoApiService.getUserInfo(kakaoToken.getAccessToken());
        User user = findOrCreateKakaoUser(userInfo);

        return createLoginResponse(user);
    }

    private User findOrCreateKakaoUser(KakaoUserInfoResponse userInfo) {
        String email = kaKaoApiService.extractEmail(userInfo);
        String nickname = kaKaoApiService.extractNickname(userInfo);

        return userRepository.findByEmail(email)
                .orElseGet(() -> createNewKakaoUser(email, nickname));
    }

    private User createNewKakaoUser(String email, String nickname) {
        String encodedPassword = passwordEncoder.encode(UUID.randomUUID().toString());
        User kakaoUser = User.createKakaoUser(nickname, email, encodedPassword);
        return userRepository.save(kakaoUser);
    }

    private LoginResponse createLoginResponse(User user) {
        String token = jwtTokenProvider.generateAccessToken(user);
        return LoginResponse.builder()
                .accessToken(token)
                .userInfo(createUserInfoResponse(user))
                .build();
    }

    private LoginResponse.UserInfo createUserInfoResponse(User user) {
        return LoginResponse.UserInfo.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .membership(user.getMembership())
                .build();
    }


}
