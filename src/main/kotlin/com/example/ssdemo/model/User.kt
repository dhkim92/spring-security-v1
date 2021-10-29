package com.example.ssdemo.model

import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "users")
class User(
        username: String,
        password: String,
        email: String,
) {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id:Int? = null

        var username: String = username
        var password: String = password
        var email: String = email

        var role: String? = null //ROLE_USER, ROLE_ADMIN

        @Column(updatable = false)
        @CreationTimestamp
        val createDate: Timestamp? = null
}