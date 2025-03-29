package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Memo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // 기본 생성자 (JPA 필수)
    public Memo () {}

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
    }

    // Getter
    public Long getId() { return id; }
    public String getTitle() {return title;}
    public String getContent() {return content;}

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
