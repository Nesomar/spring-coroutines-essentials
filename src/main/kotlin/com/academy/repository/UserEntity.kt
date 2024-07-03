package com.academy.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class UserEntity(
    @Id val id: Long? = null,
    val name: String,
    val email: String
)
