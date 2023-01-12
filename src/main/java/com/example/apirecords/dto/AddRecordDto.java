package com.example.apirecords.dto;

import lombok.Data;

public @Data class AddRecordDto {
    private Integer userId;
    private RecordDto record;
}
