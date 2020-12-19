package com.jwlee.bootPortfolio.springBoot.service.posts;

import com.jwlee.bootPortfolio.springBoot.domain.posts.Posts;
import com.jwlee.bootPortfolio.springBoot.domain.posts.PostsRepository;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsListResponseDto;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsResponseDto;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsSaveRequestDto;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository repository;

    @Transactional
    public long save(PostsSaveRequestDto reqDto) {
        return repository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public Long update(long id, PostsUpdateRequestDto requestDto) {
        Posts posts = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }


    public PostsResponseDto findById(long id){
        Posts entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return repository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void delete(long id) {
        Posts posts = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));


        repository.delete(posts);

    }

}
