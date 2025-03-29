package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // CRUD 자동으로 제공됨
}