package ru.arch.smarthouse.tempo_mock.api

import io.swagger.v3.oas.annotations.*
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import ru.arch.smarthouse.tempo_mock.adapter.kafka.KafkaProducer
import ru.arch.smarthouse.tempo_mock.api.model.DeviceControlRequest
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.collections.List
import kotlin.random.Random

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class TemperatureApiController(
    private val kafkaProducer: KafkaProducer,
) {

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
            value = randomTemerature(),
            unit = "°C"
        )))
    }

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
            value = randomTemerature(),
            unit = "°C"
        ))
    }

    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/device/{deviceId}/control"],
        consumes = ["application/json"]
    )
    fun deviceDeviceIdControlGet(@Parameter(description = "id датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String,@Parameter(description = "", required = true) @Valid @RequestBody deviceControlRequest: DeviceControlRequest): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/device/{deviceId}/produce_value"]
    )
    fun deviceDeviceIdProduceValuePost(@Parameter(description = "id датчика", required = true) @PathVariable("deviceId") deviceId: kotlin.String): ResponseEntity<Unit> {
        kafkaProducer.publish(deviceId = deviceId, value = randomTemerature())
        return ResponseEntity(HttpStatus.OK)
    }

    private fun randomTemerature() = BigDecimal(Random.nextDouble(-10.0, 40.0)).setScale(1, RoundingMode.HALF_UP).toFloat()
}
