package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    //현대 방식
    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    //과거 방식
    @RequestMapping(method = [RequestMethod.POST], path = ["request-mapping"]) // 주소 외부 노출, url 받는 형식 다르면 같아도 충돌 안남
    fun requestMapping(): String {
        return "request-mapping"
    }

    //object mapper
    //json -> object
    //object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        //json -> object
        println(userRequest)

        //UserRequest(name=Steve, age=10, email=steve@gmail.com, address=도쿄)
        return userRequest //object -> json

    }
}