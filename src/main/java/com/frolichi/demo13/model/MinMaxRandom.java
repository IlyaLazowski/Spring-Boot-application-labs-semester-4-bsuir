package com.frolichi.demo13.model;

import jakarta.persistence.*;
import lombok.*;
//LOMBOCK
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calculation")
@ToString
public class MinMaxRandom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start")
    private int startValue;
    @Column(name = "min")
    private int minRandomValue;
    @Column(name = "max")
    private int maxRandomValue;
}
