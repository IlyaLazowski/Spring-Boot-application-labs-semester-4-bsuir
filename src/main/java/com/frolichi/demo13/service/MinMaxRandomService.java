    package com.frolichi.demo13.service;

    import java.util.*;
    import java.util.concurrent.CompletableFuture;
    import java.util.stream.DoubleStream;

    import com.frolichi.demo13.dto.MinMaxRandomDto;
    import com.frolichi.demo13.mapper.MinMaxRandomMapper;
    import com.frolichi.demo13.repository.MinMaxRandomRepository;
    import com.frolichi.demo13.сache.Cache;
    import com.frolichi.demo13.model.MinMaxRandom;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.scheduling.annotation.Async;
    import org.springframework.stereotype.Service;

    @Service
    public class MinMaxRandomService {
//HYBERNET

        private final Cache cache;
        private final RequestCounterService requestCounterService;
        private final MinMaxRandomMapper minMaxRandomMapper;

        private final MinMaxRandomRepository minMaxRandomRepository;

        @Autowired
        public MinMaxRandomService(Cache cache, RequestCounterService requestCounterService, MinMaxRandomMapper minMaxRandomMapper, MinMaxRandomRepository minMaxRandomRepository) {
            this.requestCounterService = requestCounterService;
            this.cache = cache;
            this.minMaxRandomMapper = minMaxRandomMapper;
            this.minMaxRandomRepository = minMaxRandomRepository;
        }

        public MinMaxRandomService() {
            this.cache = null;
            this.requestCounterService = null;
            this.minMaxRandomMapper = null;
            this.minMaxRandomRepository = null;
        }

        public MinMaxRandom generateCacheOrObject(int key) throws InterruptedException {
            this.requestCounterService.incrementAndGet();
            MinMaxRandom value = (MinMaxRandom) cache.get(key);
            if (value == null) {
                value = generateObject(key);
                cache.put(key, value);
            }
            return value;
        }

        public MinMaxRandom generateObject(int startValue) throws InterruptedException {

            Random random = new Random();
            int maxRandomValue = random.nextInt(10000 - startValue) + startValue + 1;
            int minRandomValue = random.nextInt(startValue);
            MinMaxRandom minMaxRandom = new MinMaxRandom(null, startValue, maxRandomValue, minRandomValue);
            minMaxRandomRepository.save(minMaxRandom);
            return minMaxRandom;
        }

        public Cache getCacheService() {
            return cache;
        }

        public RequestCounterService getRequestCounterService() {
            return this.requestCounterService;
        }

        public void processBulkList(List<MinMaxRandom> minMaxRandomList) {
            this.requestCounterService.incrementAndGet();
            Logger logger = LoggerFactory.getLogger(getClass());
            minMaxRandomList.forEach(n -> {
                logger.info("MinRandomValue = {}", n.getMinRandomValue());
                logger.info("MaxRandomValue = {}", n.getMaxRandomValue());
            });
        }
//6 LAB
        public Map<String, Integer> inputDataAnalysis(List<MinMaxRandom> minMaxRandomList) {
            this.requestCounterService.incrementAndGet();
            Map<String, Integer> result = new HashMap<>();
            int min = minMaxRandomList.stream()
                    .mapToInt(MinMaxRandom::getMinRandomValue)
                    .min().orElse(0);
            int max = minMaxRandomList.stream()
                    .mapToInt(MinMaxRandom::getMaxRandomValue)
                    .max().orElse(0);
            OptionalDouble average = minMaxRandomList.stream()
                    .flatMapToDouble(n -> DoubleStream.of(n.getMaxRandomValue(), n.getMinRandomValue()))
                    .average();
            result.put("Minimum random value", min);
            result.put("Maximum random value", max);
            result.put("Average value", (int) Math.round(average.getAsDouble()));
            return result;
        }
    
        public MinMaxRandomDto getObjectById(Long id) {
            return minMaxRandomMapper.entityToDto(minMaxRandomRepository.findById(id).orElse(null));
        }

        public void deleteObjectById(Long id) {
            minMaxRandomRepository.deleteById(id);
        }

        public MinMaxRandom saveObject(MinMaxRandom minMaxRandom) {
            return minMaxRandomRepository.save(minMaxRandom);
        }

        public List<MinMaxRandom> getAll() {
            return minMaxRandomRepository.findAll();
        }

        @Async
        public CompletableFuture<MinMaxRandom> generateCacheOrObjectAsync(int key) throws InterruptedException {
            this.requestCounterService.incrementAndGet();
            MinMaxRandom value = (MinMaxRandom) cache.get(key);
            if (value == null) {
                value = generateObject(key);
                cache.put(key, value);
            }
            return CompletableFuture.completedFuture(value);
        }

    }
