package com.example.ssdemo.controller

import com.example.ssdemo.model.User
import com.example.ssdemo.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class IndexController(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    // security
    @ResponseBody
    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @ResponseBody
    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }

    @ResponseBody
    @GetMapping("/manager")
    fun manager(): String {
        return "manager"
    }


    // 스프링시큐리티가 해당 주소를 낚아챔 -> SecurityConfig 파일 생성 후 작동안함.
    @GetMapping("/loginForm")
    fun loginForm(): String {
        return "loginForm"
    }

    @GetMapping("/joinForm")
    fun joinForm(): String {
        return "joinForm"
    }

    @PostMapping("/join")
    fun join(user: User): String {
        user.role = "ROLE_USER"
        user.password = bCryptPasswordEncoder.encode(user.password)
        userRepository.save(user) // 회원가입 잘됨. 시큐리티로 로그인 불가 ( 패스워드로 암호화가 안됨 )
        return "redirect:/loginForm"
    }

    @Secured("ROLE_ADMIN") // 특정 메소드에 걸고 싶을때 간단하게 걸 수 있음
    @ResponseBody
    @GetMapping("/info")
    fun info(): String {
        return "개인 정보"
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 함수 이전에 여러 권한을 체킹할 수 있음
    @ResponseBody
    @GetMapping("/data")
    fun data(): String {
        return "데이터정보"
    }
}