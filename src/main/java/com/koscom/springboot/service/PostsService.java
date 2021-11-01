package com.koscom.springboot.service;

import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.PostsRepository;
import com.koscom.springboot.web.dto.posts.PostsSaveRequestDto;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service  //spring bean 등록 & Service 클래스 선언

public class PostsService {


   private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Posts posts = postsRepository.save(requestDto.toEntity());
        return posts.getId();
    }

    @Transactional
    public Long update(Long id , PostsUpdateRequestDto dto){

        //DB에서 가져온 값을 JPA 내부에서 캐시(1차캐시)

        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자는 없습니다. id= " + id));

        //dirty checking, 처음 값과 다르면 쿼리 날려서 저장
        entity.update(dto.getTitle(),dto.getContent());

        return entity.getId();
    }
}
