package com.koscom.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


//<Posts,Long> 은 <대상이 되는 Entity,pk타입>
//jparepository 상속 받은 인터페이스는 기본 CRUD 모두 자동 구현
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
