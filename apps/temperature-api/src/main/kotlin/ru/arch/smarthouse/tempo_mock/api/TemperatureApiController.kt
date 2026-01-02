package ru.arch.smarthouse.tempo_mock.api

import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.collections.List
import kotlin.random.Random

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class TemperatureApiController() {

    @Operation(
        summary = "Получение телеметрии датчиков из заданной локации",
        operationId = "temperatureGet",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Список телеметрий для всех датчиков из заданной локации", content = [Content(array = ArraySchema(schema = Schema(implementation = TemperatureResponse::class)))]) ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/temperature"],
        produces = ["application/json"]
    )
    fun temperatureGet(@NotNull @Parameter(description = "Локация датчиков", required = true) @Valid @RequestParam(value = "location", required = true) location: kotlin.String): ResponseEntity<List<TemperatureResponse>> {
        return ResponseEntity.ok(listOf(TemperatureResponse(
            status = "active",
            sensorId = "15",
            sensorType = "temperature",
            value = BigDecimal(Random.nextDouble(-10.0, 40.0)).setScale(1, RoundingMode.HALF_UP).toFloat(),
            unit = "°C"
        )))
    }

    @Operation(
        summary = "Получение телеметрии датчика по id",
        operationId = "temperatureSensorIdGet",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Телеметрия датчика", content = [Content(schema = Schema(implementation = TemperatureResponse::class))]) ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/temperature/{sensorId}"],
        produces = ["application/json"]
    )
    fun temperatureSensorIdGet(@Parameter(description = "id датчика", required = true) @PathVariable("sensorId") sensorId: kotlin.String): ResponseEntity<TemperatureResponse> {
        return ResponseEntity.ok(TemperatureResponse(
            status = "active",
            sensorId = "15",
            sensorType = "temperature",
            value = BigDecimal(Random.nextDouble(-10.0, 40.0)).setScale(1, RoundingMode.HALF_UP).toFloat(),
            unit = "°C"
        ))
    }
}
