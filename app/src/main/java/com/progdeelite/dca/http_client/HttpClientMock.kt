package com.progdeelite.dca.http_client

import com.progdeelite.dca.environment.Environment
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import timber.log.Timber

//(0) Pre-Requisito: Aula de Environment e Como Limpar Cookie, HttpClient

// 1) dependencias build.gradle
// 2) Entradas no proguard
// 3) Criando nosso client de mock para uso local ou playstore

object HttpClientMock {
    fun createDemoClient(clientConfig: ClientConfig): HttpClient {
        return when(clientConfig.environment){
            Environment.DEMO -> mockDemoClient
            Environment.DEV -> mockDevClient
            else -> mockLocalClient
        }
    }
}

// +--------------------------------------------------------------------+
// | CRIE AQUI SEUS TIPOS DE MOCK                                       |
// +--------------------------------------------------------------------+
private val mockDemoClient = HttpClient(MockEngine) {
    installJsonSerializer()
    installLogging()
    createMockedClient()
}

// +--------------------------------------------------------------------+
// | OUTROS TIPOS DE MOCK APENAS PARA EXEMPLIFICAR A IDEIA              |
// +--------------------------------------------------------------------+
val mockDevClient = mockDemoClient
val mockLocalClient = mockDemoClient


// +--------------------------------------------------------------------+
// | INSTALA O TIPO DE SERIALIZER QUE VOCE PRETENTE USAR                |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<MockEngineConfig>.installJsonSerializer() {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

// +--------------------------------------------------------------------+
// | INSTALA O MECANISMO DE LOG, AQUI USAMOS TIMBER                     |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<MockEngineConfig>.installLogging() {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }
        level = LogLevel.ALL
    }
}

// +--------------------------------------------------------------------+
// | CRIAR O CLIENT MOCADO PARA SEUS FINS DE TESTE                      |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<MockEngineConfig>.createMockedClient() {
    engine {
        addHandler { request ->
            val demoResponse = Demo.responseFor(request.url.fullUrl)
            val responseHeaders =
                headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
            if (demoResponse != null) {
                delay(demoResponse.delay)
                respond(demoResponse.body, demoResponse.statusCode, headers = responseHeaders)
            } else {
                error("Unhandled ${request.url.fullUrl}")
            }
        }
    }
}

// +--------------------------------------------------------------------+
// | EXTENSÃO DE AJUDA EM CIMA DE URL                                   |
// +--------------------------------------------------------------------+
private val Url.fullUrl: String get() = "${protocol.name}://$fullPath"


// +--------------------------------------------------------------------+
// | OBJETO QUE PODE SER USADO PARA CRIAR UM DEMO-MODE                  |
// +--------------------------------------------------------------------+
object Demo {

    // DEFINIR AS URL'S DE TESTE AQUI...
    private val demoUrls = mapOf(
        "https://minha.url.de.teste.1" to DemoResponse(body = "resposta demo 1"),
        "https://minha.url.de.teste.2" to DemoResponse(body = "resposta demo 2")
    )

    // RESPONDER A CADA URL'S DE ACORDO COM O CASO DE USO
    fun responseFor(url: String): DemoResponse? {
        // SE "url" MATCH, RETORNE A RESPOSTA DE DEMO CORRESPONDENTE
        val mappedResponse = demoUrls.map { if (it.key == url) it.value else null }
        return if (mappedResponse.isEmpty()) null else mappedResponse[0]
    }
}

// +--------------------------------------------------------------------+
// | RESPOSTAS PARA FINS DE TESTE OU PLAYSTORE (EXEMLO APP COM LOGIN)   |
// +--------------------------------------------------------------------+
data class DemoResponse(
    val delay: Long = 60_000,
    val body: String = "body",
    val statusCode: HttpStatusCode = HttpStatusCode.OK
)
