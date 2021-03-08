package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4XX -> Error
    // GET http://localhost:8080/api/response?age=10
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> { // 값이 없을 수도 있다 -> ?

        return age?.let {
            // age not null
            if (it < 20) {
                return ResponseEntity.status(400).body("age 값은 20보다 커야 합니다.")
            }

            ResponseEntity.ok("OK")
        } ?: kotlin.run {
            // age is null
            return ResponseEntity.status(400).body("age값이 누락되었습니다.")
        }

        /*
        // 0. age == null -> 400 error
        if (age == null){
            return ResponseEntity.status(400).body("age값이 누락되었습니다.")
        }

        // 1. age < 20 -> 400 error
        if (age < 20) {
            return ResponseEntity.status(400).body("age 값은 20보다 커야 합니다.")
        }

        return ResponseEntity.ok("OK")
         */
    }

    // 2. post 200
    // post 날아오면 이 메소드 탄다
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest) //object mapper -> object -> json
    }

    // 3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서 새로 생성. -> 201
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500 -> Error
    // 주소 무조건 맞아야 되기때문에 notNull
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(null)
    }
}