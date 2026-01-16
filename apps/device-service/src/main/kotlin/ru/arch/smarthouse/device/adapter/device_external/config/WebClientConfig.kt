package ru.arch.smarthouse.device.adapter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val properties: ExternalServiceProperties
) {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(properties.url)
            .build()
    }
}

@Component
@ConfigurationProperties(prefix = "webclient.device-external-system")
class ExternalServiceProperties {
    lateinit var url: String
    var timeout: Long = 5000
}