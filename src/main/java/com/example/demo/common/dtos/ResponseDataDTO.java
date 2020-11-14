package com.example.demo.common.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
public class ResponseDataDTO {
    private Long id;

    private String name;

    private LocalDateTime dateTime;


    public void setSignTime(Long dateTime) {
        if (dateTime != null) {
            this.dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dateTime), ZoneOffset.UTC).plusHours(9);
        }
    }
}
