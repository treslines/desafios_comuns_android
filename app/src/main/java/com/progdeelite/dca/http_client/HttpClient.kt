package com.progdeelite.dca.http_client

import android.annotation.SuppressLint
import com.progdeelite.dca.environment.Environment
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import timber.log.Timber
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

//(0) Pre-Requisito: Aula de Environment e Como Limpar Cookie

// 1) dependencias build.gradle
// 2) Entradas no proguard
// 3) Criando nossa maquina de fazer requisões HttpClient
// 4) Forcast próxima aula : Como criar um mock de DEMO

fun createHttpClient(clientConfig: ClientConfig): HttpClient {
    if (clientConfig.environment == Environment.DEMO) {
        return HttpClientMock.createDemoClient(clientConfig)
    }

    return HttpClient(OkHttp) {
        installJsonSerializer()
        installCookieConfiguration(clientConfig)
        installClientBasicAuthentication(clientConfig)
        installLogging()
        installRequestTimeouts()
        installDefaultUserAgentAndHeader(clientConfig)
        createSecureOrInsecureClient(clientConfig)
    }
}

// +--------------------------------------------------------------------+
// | INSTALA O DESERIALIZADOR DE OBJETOS JSON                           |
// +--------------------------------------------------------------------+
fun HttpClientConfig<OkHttpConfig>.installJsonSerializer() {

    install(JsonFeature) {
        // serializer = KotlinxSerializer() // OUTRA MANEIRA DE SE FAZER
        kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
        }
    }
}

// +--------------------------------------------------------------------+
// | INSTALA O STORAGE QUE SE ENCARREGA DE LIMPAR/INVALIDAR OS COOKIES  |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<OkHttpConfig>.installCookieConfiguration(
    clientConfig: ClientConfig
) {
    install(HttpCookies) {
        storage = clientConfig.cookiesStorage
    }
}

// +--------------------------------------------------------------------+
// | INSTALA O BASIC AUTHENTICATION SE ASSIM ESPECIFICADO NO AMBIENTE   |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<OkHttpConfig>.installClientBasicAuthentication(
    clientConfig: ClientConfig
) {
    clientConfig.environment.basicAuthentication()?.let {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = it.username, password = it.password)
                }
                sendWithoutRequest { true }
            }
        }
    }
}

// +--------------------------------------------------------------------+
// | INSTALA O MECANISMO DE LOG, AQUI ESTAMOS USANDO O TIMBER           |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<OkHttpConfig>.installLogging() {
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
// | INSTALA E DEFINE OS TIMEOUTS DAS REQUISICÕES PADRÃO CASO PRECISE   |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<OkHttpConfig>.installRequestTimeouts() {
    // DEFINICAO DOS TIMEOUTS DE REDE
    install(HttpTimeout) {
        requestTimeoutMillis = 60_000
        socketTimeoutMillis = 60_000
        connectTimeoutMillis = 60_000
    }
}

// +--------------------------------------------------------------------+
// | INSTALA CONFIGURACÕES PADRÃO DE USER AGENT E HEADERS CASO PRECISE  |
// +--------------------------------------------------------------------+
private fun HttpClientConfig<OkHttpConfig>.installDefaultUserAgentAndHeader(
    clientConfig: ClientConfig
) {
    // DEFINA O DEFAULT USER AGENT E ATRIBUTOS PADRÃO QUE SERÃO ENVIADOS COM TODOS OS REQUESTS
    defaultRequest {
        // SE BACKEND FOR MACKADO E PRECISAR DE ALGUMA FLAG, ATRIBUA AQUI AO HEADER
        if (clientConfig.environment.isBackendMocked()) {
            header("X-ServerMock", true)
        }
        userAgent(clientConfig.userAgent)
    }
}

// +--------------------------------------------------------------------+
// | CRIAR E RETORNA O CLIENT SEGURO OU INSEGURO DE ACORDO COM AMBIENTE |
// +--------------------------------------------------------------------+
private fun createSecureOrInsecureClient(clientConfig: ClientConfig) {
    OkHttp.create {
        preconfigured = when (clientConfig.environment) {
            Environment.DEMO, Environment.LOCAL_PRISM -> insecureClient()
            else -> secureClient(clientConfig)
        }
    }
}

// +--------------------------------------------------------------------+
// | METODO AUXILIAR PARA CRIAR UM CLIENT SEGURO PINANDO CERTIFICADOS   |
// +--------------------------------------------------------------------+
private fun secureClient(clientConfig: ClientConfig): OkHttpClient {
    val certificatePinner = CertificatePinner.Builder()
        .apply {
            clientConfig.environment.certificatePinningHashes
                .filter { it.isNotEmpty() }
                .let {
                    add(clientConfig.environment.host, *it.toTypedArray())
                }
        }
        .build()

    return OkHttpClient().newBuilder()
        .certificatePinner(certificatePinner)
        .followSslRedirects(true)
        .cache(null)
        .build()
}

// +--------------------------------------------------------------------+
// | METODO AUXILIAR PARA CRIAR UM CLIENT NÃO SEGURO                    |
// +--------------------------------------------------------------------+
@SuppressLint("CustomX509TrustManager")
private fun insecureClient(): OkHttpClient {
    val trustyManager = object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }
    val sslSocketFactory = with(SSLContext.getInstance("SSL")) {
        init(null, arrayOf(trustyManager), null)
        socketFactory
    }

    return OkHttpClient().newBuilder()
        .sslSocketFactory(sslSocketFactory, trustyManager)
        .hostnameVerifier { _, _ -> true }
        .followRedirects(true)
        .cache(null)
        .build()
}