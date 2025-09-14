package com.example.demo.user.service;

import com.example.demo.user.dto.KakaoTokenResponse;
import com.example.demo.user.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class KaKaoApiService {

    private final WebClient kakaoAuthWebClient;
    private final WebClient kakaoApiWebClient;

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    public KakaoTokenResponse getAccessToken(String authorizationCode) {
        MultiValueMap<String, String> params = createTokenRequestParams(authorizationCode);

        try {
            KakaoTokenResponse response = kakaoAuthWebClient.post()
                    .body(BodyInserters.fromFormData(params))
                    .retrieve()
                    .bodyToMono(KakaoTokenResponse.class)
                    .block();
            return response;

        } catch (Exception e) {
            throw new IllegalArgumentException("카카오 토큰 발급 중 오류가 발생했습니다.", e);
        }
    }

    private MultiValueMap<String, String> createTokenRequestParams(String authorizationCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", restApiKey);
        params.add("code", authorizationCode);

        return params;
    }

    public KakaoUserInfoResponse getUserInfo(String accessToken) {
        try {
            KakaoUserInfoResponse response = kakaoApiWebClient.get()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(KakaoUserInfoResponse.class)
                    .block();
            return response;

        } catch (Exception e) {
            throw new IllegalArgumentException("카카오 사용자 정보 조회 중 오류가 발생했습니다.", e);
        }
    }

    public String extractEmail(KakaoUserInfoResponse userInfo) {
        return Optional.ofNullable(userInfo.getKakaoAccount())
                .map(KakaoUserInfoResponse.KakaoAccount::getEmail)
                .orElseThrow(() -> new IllegalArgumentException("이메일 정보가 없습니다."));
    }

    public String extractNickname(KakaoUserInfoResponse userInfo) {
        return Optional.ofNullable(userInfo.getKakaoAccount())
                .map(KakaoUserInfoResponse.KakaoAccount::getProfile)
                .map(KakaoUserInfoResponse.KakaoAccount.Profile::getNickname)
                .orElse("카카오 사용자");
    }
}
