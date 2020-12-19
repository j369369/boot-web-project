package com.jwlee.bootPortfolio.springBoot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }


    @Test
    public void loadPosts(){
        //given
        String title = "제목";
        String content = "내용 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jwlee")
                .build());

        List<Posts> list = postsRepository.findAll();

        Posts posts = list.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,6,26,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("jwlee")
                .build());

        //when
        List<Posts> list = postsRepository.findAll();

        //then
        Posts posts = list.get(0);
        System.out.println("===============createDate=============");
        System.out.println(posts.getCreateDate());
        System.out.println("===============modifiedDate=============");
        System.out.println(posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }


}
