package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemoController{

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getAllMemos(){
        return memoRepository.findAll()
                .stream()
                .map(MemoResponseDto::new)
                .toList();
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo (@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto.getTitle(), requestDto.getContent());
        Memo saved = memoRepository.save(memo);
        return new MemoResponseDto(saved);
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable("id") Long id) {
        if (memoRepository.existsById(id)) {
            memoRepository.deleteById(id);
        }
    }

    @PutMapping("/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable("id") Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메모 없음: " + id));

        memo.setTitle(requestDto.getTitle());
        memo.setContent(requestDto.getContent());

        Memo updated = memoRepository.save(memo);
        return new MemoResponseDto(updated);
}
}