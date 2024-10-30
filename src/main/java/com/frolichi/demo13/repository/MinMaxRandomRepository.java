package com.frolichi.demo13.repository;

import com.frolichi.demo13.model.MinMaxRandom;
import org.springframework.data.jpa.repository.JpaRepository;


//Repository which answer for data operations(read, write, delete in BD)
public interface MinMaxRandomRepository extends JpaRepository<MinMaxRandom, Long> {
}
