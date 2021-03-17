package com.example.mvc.advice

import org.springframework.web.bind.annotation.ExceptionHandler

//@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class]) -> Target 설정 가능
//@RestControllerAdvice   // RestController의 Exception이 이 컨트롤러를 통하게 됨(Global : 괄호X)
class GlobalControllerAdvice {  // 특정 예외를 잡겠다고 지정

    @ExceptionHandler(value = [RuntimeException::class])    //Runtime Error 터지면 아무거나 다잡음 -> 지정하지 않았는데 처리 되었음 -> Advice 명시 해주거나, 정말 표준적인 것만 처리
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

//    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
//    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> { // 200 OK, Catch 됨
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
//    }
// 고객사를 위한 익셉션.. 등등을 글로벌 하게 잡아 줄 수 있
// 특정 클래스 , 특정 함수 등 특정할 수 도 있다.

}