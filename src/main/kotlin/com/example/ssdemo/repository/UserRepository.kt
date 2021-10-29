package com.example.ssdemo.repository

import com.example.ssdemo.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String?): User?
}