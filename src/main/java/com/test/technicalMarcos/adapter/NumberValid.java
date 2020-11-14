package com.test.technicalMarcos.adapter;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NumberValid {

    NeutrinoAdapter neutrinoAdapter;

    public NumberValid() {
        neutrinoAdapter = new NeutrinoAdapter();
    }

    public boolean isNumberValid(String number) {

        ResponseEntity<String> jsonString = neutrinoAdapter.callNeutrinoAPI(number);
        JSONObject json = new JSONObject(Objects.requireNonNull(jsonString.getBody()));
        return (boolean) json.get("valid");
    }

}
