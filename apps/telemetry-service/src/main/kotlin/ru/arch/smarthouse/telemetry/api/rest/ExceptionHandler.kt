package ru.arch.smarthouse.telemetry.api.rest

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.arch.smarthouse.telemetry.model.exception.NotFoundException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException) : ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "error" to "Not found",
            ),
            HttpStatus.NOT_FOUND,
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun nonConstraintViolationException(ex: DataIntegrityViolationException) : ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "error" to ex.cause.toString().take(50),
            ),
            HttpStatus.CONFLICT,
        )
    }

    @ExceptionHandler(Exception::class)
    fun exception(ex: Exception): ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "error" to ex.cause.toString().take(50),
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}