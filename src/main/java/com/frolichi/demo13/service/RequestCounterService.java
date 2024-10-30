package com.frolichi.demo13.service;

import org.springframework.stereotype.Service;

@Service
public class RequestCounterService {

    private int counter = 0;

    public synchronized int incrementAndGet(){
        this.counter++;
        return this.counter;
    }

    public int getCount(){
        return this.counter;
    }

}
