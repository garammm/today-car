package com.todaycar.board.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "feed")  
public class CarBoardDocument {
    @Id
    private String id;
    private Long originalId;  // H2 DB의 ID
    private String title;
    private String content;
    private String author;
    private String imageUrl;
    private LocalDateTime createdAt;

    // 생성자
    public CarBoardDocument() {}

    public CarBoardDocument(CarBoard carBoard) {
        this.originalId = carBoard.getId();
        this.title = carBoard.getTitle();
        this.content = carBoard.getContent();
        this.author = carBoard.getAuthor();
        this.imageUrl = carBoard.getImageUrl();
        this.createdAt = carBoard.getCreatedAt();
    }

    // getter/setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getOriginalId() { return originalId; }
    public void setOriginalId(Long originalId) { this.originalId = originalId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}