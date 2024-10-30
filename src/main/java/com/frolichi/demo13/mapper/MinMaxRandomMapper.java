package com.frolichi.demo13.mapper;

import com.frolichi.demo13.dto.MinMaxRandomDto;
import com.frolichi.demo13.model.MinMaxRandom;
import org.springframework.stereotype.Component;

@Component
public class MinMaxRandomMapper implements IMapper<MinMaxRandomDto, MinMaxRandom>{
    @Override
    public MinMaxRandomDto entityToDto(MinMaxRandom entity) {
        return new MinMaxRandomDto(entity.getId(), entity.getStartValue() , entity.getMinRandomValue(), entity.getMaxRandomValue());
    }

    @Override
    public MinMaxRandom dtoToEntity(MinMaxRandomDto dto) {
        return new MinMaxRandom(dto.getId(), dto.getStartValue() , dto.getMinRandomValue(), dto.getMaxRandomValue());
    }
}
