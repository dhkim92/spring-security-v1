package com.example.ssdemo.config.auth

import com.example.ssdemo.model.User
import com.example.ssdemo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행 (규칙)

@Service
class PrincipalDetailsService(private var userRepository: UserRepository) : UserDetailsService {

    // 시큐리티 session(Authentication(userDetails)
    override fun loadUserByUsername(username: String?): UserDetails {
        var userEntity: User? = userRepository.findByUsername(username)
        if (userEntity != null) {
            return PrincipalDetails(userEntity)
        }
        throw NoSuchElementException()
    }
}