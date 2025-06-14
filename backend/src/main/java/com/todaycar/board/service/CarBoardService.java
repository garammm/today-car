package com.todaycar.board.service;

import com.todaycar.board.repository.CarBoardRepository;
import com.todaycar.board.repository.CarBoardMongoRepository;
import com.todaycar.board.domain.CarBoard;
import com.todaycar.board.domain.CarBoardDocument;
import com.todaycar.board.dto.CarBoardRequestDto;
import com.todaycar.board.dto.CarBoardResponseDto;
import com.todaycar.config.DataSourceConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBoardService {
    private final CarBoardRepository repository;
    private final CarBoardMongoRepository mongoRepository;

    public CarBoardService(CarBoardRepository repository,
                         CarBoardMongoRepository mongoRepository) {
        this.repository = repository;
        this.mongoRepository = mongoRepository;
    }    @Transactional(readOnly = true)
    public List<CarBoardResponseDto> getAll() {
        DataSourceConfig.DataSourceContextHolder.setDataSourceType("read");
        try {
            return mongoRepository.findAllByOrderByCreatedAtDesc()
                    .stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());
        } finally {
            DataSourceConfig.DataSourceContextHolder.clearDataSourceType();
        }
    }
    
    @Transactional
    public CarBoardResponseDto create(CarBoardRequestDto dto) {
        DataSourceConfig.DataSourceContextHolder.setDataSourceType("write");
        try {
            CarBoard entity = new CarBoard();
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setAuthor(dto.getAuthor());
            entity.setImageUrl(dto.getImageUrl());
            CarBoard saved = repository.save(entity);
            // H2에 저장 후, MongoDB에도 저장
            CarBoardDocument document = new CarBoardDocument(saved);
            mongoRepository.save(document);
            return toResponseDto(saved);
        } finally {
            DataSourceConfig.DataSourceContextHolder.clearDataSourceType();
        }
    }

    private CarBoardResponseDto toResponseDto(CarBoard entity) {
        CarBoardResponseDto dto = new CarBoardResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setAuthor(entity.getAuthor());
        dto.setImageUrl(entity.getImageUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private CarBoardResponseDto toResponseDto(CarBoardDocument document) {
        CarBoardResponseDto dto = new CarBoardResponseDto();
        dto.setId(document.getOriginalId());
        dto.setTitle(document.getTitle());
        dto.setContent(document.getContent());
        dto.setAuthor(document.getAuthor());
        dto.setImageUrl(document.getImageUrl());
        dto.setCreatedAt(document.getCreatedAt());
        return dto;
    }
}
