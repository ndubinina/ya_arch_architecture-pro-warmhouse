package ru.arch.smarthouse.device.adapter.device_external

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.arch.smarthouse.device.model.Device

@Component
class AdapterDeviceExternalSystem(
    private val webClient: WebClient,
) {
    fun control(device: Device, value: String, status: String) {
        val request = mapOf(
            "value" to value,
            "status" to status,
        )
        val httpStatus = webClient.patch()
            .uri("/{id}/control", device.externalId)
            .bodyValue(request)
            .exchangeToMono { clientResponse ->
                clientResponse.statusCode().let { Mono.just(it) }
            }
            .block()!!

        if (httpStatus != HttpStatus.ACCEPTED) {
            throw RuntimeException("DevicesExternal HttpStatus: ${httpStatus}")
        }
    }
}