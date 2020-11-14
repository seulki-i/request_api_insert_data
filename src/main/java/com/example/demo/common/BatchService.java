package com.example.demo.common;

import com.example.demo.common.exception.BatchInitException;
import org.springframework.http.HttpHeaders;

public interface BatchService {
    void requestData(String url, HttpHeaders headers) throws BatchInitException;

    void requestDate() throws BatchInitException;
}
