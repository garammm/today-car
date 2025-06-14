package com.todaycar.board.repository;

import com.todaycar.board.domain.CarBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBoardRepository extends JpaRepository<CarBoard, Long> {
}
