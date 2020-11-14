package com.example.demo.common;

import com.example.demo.common.dtos.JsonInfoDTO;
import com.example.demo.common.dtos.ResponseDataDTO;
import com.example.demo.common.exception.BatchInitException;
import com.example.demo.target.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResponseService responseService;

    private static final Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

    @Value("${apiName.url}")
    private String apiUrl;

    @Override
    public void requestData(String url, HttpHeaders headers) throws BatchInitException {

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        JsonInfoDTO<ResponseDataDTO> response = restTemplate.exchange(url, HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<JsonInfoDTO<ResponseDataDTO>>() {
                }).getBody();

        logger.info("request...end...");

        if (response.getCode() == 200) {
            responseService.insertData(response.getData());
        } else {
            throw new BatchInitException(String.format("code : %s, msg : %s", response.getCode(), response.getMessage()));
        }
    }

    @Override
    public void requestDate() throws BatchInitException {
        String url = apiUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        requestData(url, headers);
    }
}
