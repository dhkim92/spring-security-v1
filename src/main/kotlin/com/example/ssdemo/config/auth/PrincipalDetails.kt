package com.example.ssdemo.config.auth

import com.example.ssdemo.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// 시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어준다. (Security ContextHolder 라는 키값에다가 세션 정보를 저장시킨다 ) -> 같은 세션 공간안에 따로 만드는 것
// 시큐리티가 가지고 있는 세션에 들어갈 수 있는 오브젝트 => Authentication 타입 객체 여야 한다.
// Authentication 안에 User 정보가 있어야 됨.
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails)

class PrincipalDetails(private var user: User) : UserDetails {

    // 해당 유저의 권한을 리턴하는 곳!
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val collect: MutableCollection<GrantedAuthority> = ArrayList()
        collect.add(GrantedAuthority{user.role})
        return collect
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    // 계정 만료됐니?
    override fun isAccountNonExpired(): Boolean {
        return true // 아니요
    }

    // 계정 잠겼니?
    override fun isAccountNonLocked(): Boolean {
        return true // 아니요
    }

    // 계정 너무 오래 사용한거 아니니
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    //게정 활성화 되어있니?
    // 우리 사이트에서 1년동안 회원이 로그인을 안하면!! 휴먼계정으로 하기로 함
    override fun isEnabled(): Boolean {
        return true // 아니요
     }

}