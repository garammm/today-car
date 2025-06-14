package com.todaycar.board.controller;

import com.todaycar.board.service.CarBoardService;
import com.todaycar.board.dto.CarBoardRequestDto;
import com.todaycar.board.dto.CarBoardResponseDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class CarBoardController {
    private final CarBoardService service;
    public CarBoardController(CarBoardService service) {
        this.service = service;
    }
    @GetMapping
    public List<CarBoardResponseDto> getAll() {
        return service.getAll();
    }
    @PostMapping
    public CarBoardResponseDto create(@RequestBody CarBoardRequestDto dto) {
        return service.create(dto);
    }
}
