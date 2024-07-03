package com.academy.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : ReactiveCrudRepository<UserEntity, Long> {
}