package com.koscom.springboot.web;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import sun.reflect.annotation.ExceptionProxy;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)  // juit을 spirng 형태로 테스트
@WebMvcTest  // web 관련 테스트시  @Springboottest 도 가능하지만 불필요한 것까지 다 끌어올린다.
public class HelloControllerTest {

    @Autowired    // bean들 정의
    MockMvc mvc;


    @Test
    void hello주소로요청이오면_hello가_리턴된다() throws Exception{
        String expectResult = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectResult));



    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))  //여기까지 request 테스트
                .andExpect(status().isOk())                                    //검증시작
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }

    @Test
    void amount가_없으면_응답코드가_400이_된다() throws Exception {
        String name = "hello";

        mvc.perform(get("/hello/dto")
                .param("name",name))
                .andExpect(status().isBadRequest());
    }
}
