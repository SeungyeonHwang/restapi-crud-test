package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    // 가질수 있는 것
    // 1. path variable
    // 2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        // 이름 지정 가능
        @RequestParam(value = "name") _name: String,
        @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") _age: Int): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }
}