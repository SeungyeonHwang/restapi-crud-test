package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {  // 연습용 Api, 실무는 X

    @GetMapping("/hello")
    fun hello() {
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 6)
        @RequestParam name: String,

        @Min(10)
        @RequestParam age: Int
    ): String {
        //통과된 경우만 -> print(Validated)
        println(name)
        println(age)
        return name + " " + age
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {  // bindingResult: BindingResult  -> 결과 받아준다, Validation 사용하기 때문에 삭제
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class]) // createdAt -> 형식 오류
    fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {   //매개 변수 설정
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {

                this.field = (errorObject as FieldError).field  // 형변환 field Name 불러 올 수 있음
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue  //rejected Value
            }
            errors.add(error)
        }

        // 2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()   // 현재 request를 찾아서 주입해 줌
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. Return -> ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])    // 어떤 에러를 가지는지 가져올 수 있음, 이번에는 GET, requestParam Error(Validation)
    fun constraintVaolationException(
        e: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        // 1. 에러 분석
        val errors = mutableListOf<Error>() // 에러 하나하나 넣어준다 : forEach

        e.constraintViolations.forEach {
            val error = Error().apply {
                this.field = it.propertyPath.last().name // 마지막에 변수 이름 들어 있음
                this.message = it.message
                this.value = it.invalidValue
            }
            errors.add(error)
        }

        // 2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()   // 현재 request를 찾아서 주입해 줌
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. Return -> ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

//        if (true) {
//            throw RuntimeException("강제 exception 발생")   //hello method 실행되면 바로 Exception -> java.lang.RuntimeException: 강제 exception 발생
//        }

    // 컨트롤러 내부에 Exception Handler가 있다면 Advice타지 않고 이 곳을 탄다 , 각 장단점이 있다.
    // 클래스 내부의 경우는 해당 컨트롤러 안에서만 일어나는 예외 한번에 처리 가능
    // 너무 예외가 많아지면 가독성이 떨어지는 단점이 있다.
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> { // 200 OK, Catch 됨
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

}