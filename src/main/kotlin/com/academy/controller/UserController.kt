package com.academy.controller

import com.academy.repository.UserEntity
import com.academy.service.CsvService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/csv")
class CsvController(private val csvService: CsvService) {

    @PostMapping("/upload/", consumes = ["multipart/form-data"])
    fun uploadCsv(
        @RequestPart("file") file: MultipartFile,
        @RequestParam("delimiter") delimiter: Char
    ): ResponseEntity<Flux<UserEntity>> {
        val users = csvService.processCsv(file, delimiter)
        return ResponseEntity(users, HttpStatus.CREATED)
    }

    @GetMapping("/reader")
    fun get(): ResponseEntity<Flux<UserEntity>> {
        return ResponseEntity.ok(Flux.just(UserEntity(1, "Nesomar", "nesomar@emil.com")))
    }
}