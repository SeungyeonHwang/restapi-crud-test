package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController //REST API
@RequestMapping("/api") //localhost:8080/api
class GetApiController {

    //Get 주소를 노출할때 사용 하는 방법 : 최근
    //@GetMapping("/hello") : http://localhost:8080/api/hello
    @GetMapping(path = ["/hello", "/abcd"]) //Get http://localhost:8080/api/hello, Get http://localhost:8080/api/abcd
    fun hello(): String {
        return "hello kotlin"
    }

    //Get,Post 관계없이 주소를 노출할때 사용 하는 방법 : 과거
    @RequestMapping(method = [RequestMethod.GET], path = ["request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") //http://localhost:8080//get-mapping/path-variable/steve/1 : steve 1
    fun pathVariable(
        @PathVariable name: String,
        @PathVariable age: Int
    ): String {
        println("${name} , ${age}")
        return name + " " + age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") //http://localhost:8080//get-mapping/path-variable/steve/1 : steve 1
    fun pathVariable2(
        @PathVariable(value = "name") _name: String,
        @PathVariable(name = "age") age: Int
    ): String {

        val name = "kotlin"

        println("${_name} , ${age}")
        return _name + " " + age
    }

    // queryParameter : Key는 중복되어서는 안된다
    // http://localhost:8080/api/page?key=value&key=value&key=value
    @GetMapping("/get-mapping/query-param") // ?name=steve&age=20
    fun queryParam(
        @RequestParam name: String,
        @RequestParam(value = "age") age: Int
    ): String {
        println("${name} , ${age}")
        return name + " " + age
    }

    // QueryParameter 객체로 받는 방법
    // name, age, address, email
    // -
    // phoneNumber -> phone-number(Kotlin에서는 변수에 - 넣을 수 없기 때문에 name or value = ) , phonenumber
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest { //RESTController 에서 Return Object -> Json Type
        println(userRequest)
        return userRequest
    }

    //phone-number -> 가능 (-)
    //(-) 받는 방법 1.QueryParam 2.Map
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map["phone-number"]
        return map
    }
}