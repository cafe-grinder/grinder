package com.grinder.utils;

import com.grinder.domain.dto.AlanDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Objects;

@Component
public class AlanAPI {
    @Value("${alan.secret}")
    private String key;

    public AlanDTO.AlanResponse requestSummary(String cafeName, String address) {
        AlanDTO.AlanResponse alanResponse = new AlanDTO.AlanResponse();
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder url = new StringBuilder();
        url.append("https://kdt-api-function.azurewebsites.net/api/v1/question?content=")
                .append("카페명 : ").append(cafeName).append(" 주소명 :").append(address)
                .append("에 대해 1.분위기, 2. 맛, 3. 가격 형식으로 검색해서 250자 이내로 정리해서 알려줘.")
                .append("&client_id=").append(key);

        ResponseEntity<LinkedHashMap> response = restTemplate.getForEntity(url.toString(), LinkedHashMap.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            LinkedHashMap<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                LinkedHashMap<String, String> action = (LinkedHashMap<String, String>) responseBody.get("action");
                alanResponse.setActionName(action.get("name"));
                alanResponse.setActionSpeak(action.get("speak"));
                if (responseBody.get("content").toString().length() > 250) {
                    alanResponse.setContent(responseBody.get("content").toString().substring(0,250));
                    return alanResponse;
                }
                alanResponse.setContent((String) responseBody.get("content"));
            }
        } else throw new IllegalArgumentException("잘못된 입력입니다.");

        return alanResponse;
    }

    public AlanDTO.AlanResponse anyQuestion(String question) {
        AlanDTO.AlanResponse alanResponse = new AlanDTO.AlanResponse();
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder url = new StringBuilder();
        url.append("https://kdt-api-function.azurewebsites.net/api/v1/question?content=")
                .append(question)
                .append("&client_id=").append(key);

        ResponseEntity<LinkedHashMap> response = restTemplate.getForEntity(url.toString(), LinkedHashMap.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            LinkedHashMap<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                LinkedHashMap<String, String> action = (LinkedHashMap<String, String>) responseBody.get("action");
                alanResponse.setActionName(action.get("name"));
                alanResponse.setActionSpeak(action.get("speak"));
                alanResponse.setContent((String) responseBody.get("content"));
            }
        } else throw new IllegalArgumentException("잘못된 입력입니다.");

        return alanResponse;
    }
}











