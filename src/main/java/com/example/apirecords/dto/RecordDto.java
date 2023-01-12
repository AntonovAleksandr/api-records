package com.example.apirecords.dto;

import lombok.Data;

public @Data class RecordDto {
    private int id;

    private String date;

    private String time;

    private String status;

    private String result;

    private String data;
}
