package com.todaycar.board.repository;

import com.todaycar.board.domain.CarBoardDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarBoardMongoRepository extends MongoRepository<CarBoardDocument, String> {
    List<CarBoardDocument> findAllByOrderByCreatedAtDesc();
}
