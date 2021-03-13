package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    //Post와 동일, Put -> 내용없으면 생성, 있으면 수정
    //Bean에 Vaildation 적용할려면 @Vaild 필요
    //BindingResult -> Bean 에러 분기 나눠서 검출
    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(
        @Valid @RequestBody userRequest: UserRequest,
        bindingResult: BindingResult    //Vaild -> BindingResult -> hasError? -> logic 탄다
    ): ResponseEntity<String> {

        if (bindingResult.hasErrors()) {
            // 500 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError    // FieldError 로 형변환
                val message = it.defaultMessage     //Message Customize
                msg.append("${field.field} : $message\n")   //메세지 합치기
//                name : 크기가 2에서 8 사이여야 합니다
//                name : 비어 있을 수 없습니다
//                age : 0 이상이어야 합니다
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")
    }
}
        //0. Response

//        return UserResponse().apply {
//
//            //1. result
//            this.result = Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//        }.apply {
//            //2. description
//            this.description = "~~~~~~~~~~~~"
//
//        }.apply {
//
//            //3. user mutable list
//            val userList = mutableListOf<UserRequest>()
//            userList.add(userRequest)
//            userList.add(UserRequest().apply {
//                this.name = "a"
//                this.age = 10
//                this.email = "a@gmail.com"
//                this.address = "a address"
//                this.phoneNumber = "010-1111-aaaa"
//            })
//            userList.add(UserRequest().apply {
//                this.name = "b"
//                this.age = 20
//                this.email = "b@gmail.com"
//                this.address = "b address"
//                this.phoneNumber = "010-1111-bbbb"
//            })
//
//            this.user = userList
//        }
//    }
//}