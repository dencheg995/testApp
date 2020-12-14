package ru.test.app.controller;

import org.springframework.http.HttpHeaders;

public abstract class AbstractController {

    public static final String MAIN_PATH = "/api/v1";

    protected HttpHeaders getHttpHeadersDescription(String value) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("description", value);
        return httpHeaders;
    }
}
