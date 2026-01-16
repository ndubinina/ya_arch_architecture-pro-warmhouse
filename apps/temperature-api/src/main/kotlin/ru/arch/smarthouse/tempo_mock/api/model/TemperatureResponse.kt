package ru.arch.smarthouse.tempo_mock.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param status 
 * @param sensorId 
 * @param sensorType 
 * @param &#x60;value&#x60; 
 * @param unit 
 * @param timestamp 
 * @param location 
 * @param description 
 */
data class TemperatureResponse(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("status", required = true) val status: String,

    @Schema(example = "null", description = "")
    @get:JsonProperty("sensor_id") val sensorId: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("sensor_type") val sensorType: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("value") val `value`: Float? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("unit") val unit: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("timestamp") val timestamp: java.time.OffsetDateTime? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("location") val location: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("description") val description: String? = null
) {

}

