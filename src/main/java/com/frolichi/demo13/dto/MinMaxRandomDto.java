package com.frolichi.demo13.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//DTO use for transfer data, it is simple object
@Getter
@Setter
@AllArgsConstructor
public class MinMaxRandomDto {
    private Long id;
    private int startValue;
    private int minRandomValue;
    private int maxRandomValue;
}
