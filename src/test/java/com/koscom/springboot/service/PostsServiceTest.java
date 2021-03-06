package com.koscom.springboot.service;

import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.PostsRepository;
import com.koscom.springboot.web.dto.posts.PostsSaveRequestDto;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class PostsServiceTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsService postsService;

    @AfterEach
    void tearDown(){
        postsRepository.deleteAll();
    }

    @Test
    void postService를_통해서_저장이_된다(){
        String test = "test";
        String content = "test2";
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title(test)
                .content(content)
                .build();
        postsService.save(dto);

        List<Posts> result = postsRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(test);
        assertThat(result.get(0).getContent()).isEqualTo(content);
    }
    @Test
    void postService를_통해서_수정이_된다(){

        //미리 저장된 값을 하나 생성한다 ("1","2");
        Posts save = postsRepository.save(Posts.builder()
                .title("1")
                .content("2")
                .build());

        System.out.println("save.title= "+ save.getTitle());
        System.out.println("save.content= "+ save.getContent());

        String test = "test";
        String content = "test2";

        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title(test)
                .content(content)
                .build();
        postsService.update(save.getId(),dto);

        List<Posts> result = postsRepository.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(test);
        assertThat(result.get(0).getContent()).isEqualTo(content);
    }
}
