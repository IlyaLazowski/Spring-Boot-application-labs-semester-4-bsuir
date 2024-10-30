package com.frolichi.demo13.service;
import com.frolichi.demo13.model.MinMaxRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GenerateRandomServiceTest {

    @Test
    public void testGenerateObject() throws InterruptedException {
        MinMaxRandomService service = new MinMaxRandomService();
        MinMaxRandom result = service.generateObject(5000);
        Assertions.assertTrue(result.getMinRandomValue() >= 1);
        Assertions.assertTrue(result.getMaxRandomValue() > result.getMinRandomValue());
        Assertions.assertTrue(result.getMaxRandomValue() <= 10000);
    }

    @Test
    public void testGenerateObjectWithTooSmallStartValue() {
        MinMaxRandomService service = new MinMaxRandomService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.generateObject(0));
    }

    @Test
    public void testGenerateObjectWithTooLargeStartValue() {//rename
        MinMaxRandomService service = new MinMaxRandomService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.generateObject(10001));
    }
}
