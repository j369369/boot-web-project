package com.jwlee.bootPortfolio.springBoot.web;

import com.jwlee.bootPortfolio.springBoot.service.posts.PostsService;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsResponseDto;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsSaveRequestDto;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto reqDto){
        return postsService.save(reqDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable long id){
        return postsService.findById(id);
    }


    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable long id){
        postsService.delete(id);
        return id;
    }

}
