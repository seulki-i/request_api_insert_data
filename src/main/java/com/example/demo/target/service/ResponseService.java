package com.example.demo.target.service;

import com.example.demo.common.dtos.ResponseDataDTO;

import java.util.List;

public interface ResponseService {
    void insertData(List<ResponseDataDTO> data);
}
