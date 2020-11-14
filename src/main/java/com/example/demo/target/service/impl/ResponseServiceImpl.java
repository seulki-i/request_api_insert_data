package com.example.demo.target.service.impl;

import com.example.demo.common.dtos.ResponseDataDTO;
import com.example.demo.target.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertData(List<ResponseDataDTO> data) {
        String insertQuery =
                "INSERT INTO event_test (id, name, date_time) " +
                        "VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ResponseDataDTO responseDataDTO = data.get(i);

                ps.setLong(1, responseDataDTO.getId());
                ps.setTimestamp(2, Timestamp.valueOf(responseDataDTO.getDateTime()));
                ps.setString(3, responseDataDTO.getName());
            }

            @Override
            public int getBatchSize() {
                return data.size();
            }
        });
    }
}
