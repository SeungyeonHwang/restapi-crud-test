package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

// 특정한 HtmlPage 내릴때 사용하는 어노테이션(static 폴더 하위)
@Controller
class PageController {

    // http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String {    // text "main.html"
        println("init main")
        return "main.html"
    }

    // Controller 페이지 관련 컨트롤러에서 Json String 내리거나 할때 붙여줌
    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name = "steve"
        }
//        return "main.html"

    }
}