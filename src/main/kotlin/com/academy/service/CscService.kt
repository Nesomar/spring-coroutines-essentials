package com.academy.service


import com.academy.repository.UserEntity
import com.academy.repository.UserRepository
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import java.io.InputStreamReader

@Service
class CsvService(private val userRepository: UserRepository) {

    fun processCsv(file: MultipartFile, delimiter: Char): Flux<UserEntity> {
        val csvMapper = CsvMapper()
        val schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(delimiter)
        val reader = InputStreamReader(file.inputStream)

        val users = csvMapper.readerFor(UserEntity::class.java).with(schema).readValues<UserEntity>(reader).readAll()

        return userRepository.saveAll(users)
    }
}