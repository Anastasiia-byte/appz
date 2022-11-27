package com.example.appz.dtos;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private T data;
}
