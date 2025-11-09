package com.harshal.learning.service;

import com.harshal.learning.api.response.WeatherResponse;
import com.harshal.learning.cache.CacheConfig;
import com.harshal.learning.constants.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private CacheConfig cacheConfig;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String API = cacheConfig.cacheMap.get(CacheConfig.keys.api.toString());
        String finalApi=API.replace(PlaceHolder.apiKey,apiKey).replace(PlaceHolder.city,city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
