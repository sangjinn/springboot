package com.koscom.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString  // 오브젝트의 실제값을 찍고싶으면 tostring으로 가져올 수 있다.
@Getter    //get 함수 안만들어도 된다.
@RequiredArgsConstructor  //  final이 포함된 변수들을 포함한 생성자를 만든다
public class HelloResponseDto {

    private final String name;
    private final int amount;



}
