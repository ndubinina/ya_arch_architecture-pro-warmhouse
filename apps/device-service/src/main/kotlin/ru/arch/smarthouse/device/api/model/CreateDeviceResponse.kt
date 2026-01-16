package ru.arch.smarthouse.device.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param deviceId идентификатор датчика
 */
data class CreateDeviceResponse(

    @Schema(example = "null", required = true, description = "идентификатор датчика")
    @get:JsonProperty("deviceId", required = true) val deviceId: String
) {

}

