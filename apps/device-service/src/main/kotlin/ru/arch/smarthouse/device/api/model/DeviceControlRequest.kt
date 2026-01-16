package ru.arch.smarthouse.device.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param &#x60;value&#x60; значение показателя
 * @param status статус датчика(active, inactive)
 */
data class DeviceControlRequest(

    @Schema(example = "null", required = true, description = "значение показателя")
    @get:JsonProperty("value", required = true) val `value`: String,

    @Schema(example = "null", required = true, description = "статус датчика(active, inactive)")
    @get:JsonProperty("status", required = true) val status: String
) {

}

