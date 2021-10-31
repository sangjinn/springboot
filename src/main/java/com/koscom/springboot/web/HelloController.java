package com.koscom.springboot.web;

import com.koscom.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  //http json 응답 줄 수 있는 형태  api 응답, 일단 컨트롤러는 jsp 응답,
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    //getA?name =A&age=20
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name")String name,
                                     @RequestParam("amount")int amount){
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        System.out.println("dto = " + dto);
        return dto;
    }
}
