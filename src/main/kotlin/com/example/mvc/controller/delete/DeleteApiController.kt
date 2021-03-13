package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

// https://beanvalidation.org/2.0-jsr380/spec/
// JSR-320
// hibernate Validation
// Spring boot Validation
@RestController
@RequestMapping("/api")
@Validated //_age는 Bean이 아니기 때문에 어노테이션 필요
class DeleteApiController {

    // 가질수 있는 것
    // 1. path variable
    // 2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        // 이름 지정 가능
        @RequestParam(value = "name") _name: String,

        //Validation
        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(20, message = "20보다 커야 합니다.")

        @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @PathVariable(value = "name")
        @Size(min = 2, max = 5, message = "name의 길이는 2~5")
        @NotNull
        _name: String, // aa ~ aaaaa

        //Validation
        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(20, message = "20보다 커야 합니다.")
        @PathVariable(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }
}