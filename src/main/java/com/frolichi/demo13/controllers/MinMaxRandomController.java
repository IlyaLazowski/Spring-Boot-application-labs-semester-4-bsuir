package com.frolichi.demo13.controllers;

import com.frolichi.demo13.dto.MinMaxRandomDto;
import com.frolichi.demo13.model.MinMaxRandom;

import java.util.List;

import com.frolichi.demo13.service.MinMaxRandomService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
@Validated
public class MinMaxRandomController {
    private final MinMaxRandomService service;
    private static final Logger logger = LoggerFactory.getLogger(MinMaxRandomController.class);

    @Autowired
    MinMaxRandomController(MinMaxRandomService service) {
        this.service = service;
    }

    @GetMapping(value = "/generate-value")
    public MinMaxRandom generateRandomNumbers(@RequestParam @Min(1) @Max(10000) int startValue) throws InterruptedException {
        logger.info("Получен запрос на генерацию случайных чисел с начальным значением: " + startValue);
        return (MinMaxRandom) this.service.generateCacheOrObject(startValue);
    }

    @GetMapping("/cache-clear")
    public void cacheClear() {
        service.getCacheService().clearAll();
    }


    @GetMapping("/get-count_request")
    public Map<String, Integer> getCountRequestForMainService() {
        Map<String, Integer> count = new HashMap<>();
        count.put("count", service.getRequestCounterService().getCount());
        return count;
    }

    @PostMapping("/bulk_test")
    public Map<String, Integer> handleBulkListRequest2(@RequestBody List<MinMaxRandom> minMaxRandomList) {
        return this.service.inputDataAnalysis(minMaxRandomList);
    }

    @GetMapping("/get_list_object")
    public List<MinMaxRandom> getAllUsers() {
        return service.getAll();
    }


    @GetMapping("/look/{id}")
    public MinMaxRandomDto getUserById(@PathVariable Long id) {
        return service.getObjectById(id);
    }

    @PostMapping("/create_object")
    public MinMaxRandom createObject(@RequestBody MinMaxRandom minMaxRandom) {
        return service.saveObject(minMaxRandom);
    }
    @DeleteMapping("/delete_bu_id/{id}")
    public void deleteObject(@PathVariable Long id) {
        service.deleteObjectById(id);
    }

    @GetMapping(value = "/generate_value_async")
    public ResponseEntity<String> generateRandomNumbersAsync(@RequestParam @Min(1) @Max(10000) int startValue) throws InterruptedException {
        logger.info("Получен запрос на генерацию случайных чисел с начальным значением: " + startValue);
        CompletableFuture<MinMaxRandom> future = this.service.generateCacheOrObjectAsync(startValue);
        return ResponseEntity.accepted().body("ASYNCHRONOUS CALL ACCEPTED");
    }





}
