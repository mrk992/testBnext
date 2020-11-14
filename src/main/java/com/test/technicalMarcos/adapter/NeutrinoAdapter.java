package com.test.technicalMarcos.adapter;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class NeutrinoAdapter {

    private String endpoint = "https://neutrinoapi.net/phone-validate";

    private String userId = "mrk992";

    private String apiKey = "55FzBdhZ5X75AspYPWTsvntUakeTx1Xb14Wu8Dj0aAzPnmXG";

    public ResponseEntity<String> callNeutrinoAPI(String number) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("user-id", userId)
                .queryParam("api-key", apiKey)
                .queryParam("number", number);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

    }
}
