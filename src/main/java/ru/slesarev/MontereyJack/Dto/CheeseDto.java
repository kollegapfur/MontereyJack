package ru.slesarev.MontereyJack.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheeseDto {

    private long id;

    private String name;

    private int age;

    private double price;
}
