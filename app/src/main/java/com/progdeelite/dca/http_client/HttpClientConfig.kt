package com.progdeelite.dca.http_client

import com.progdeelite.dca.environment.Environment
import io.ktor.client.features.cookies.*
import io.ktor.http.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// 0) Pre-Requisito: Aula Definindo Ambientes

// 1) definir interface para limpar cookies (dentro do cookie storage)
// 2) Especificar dependencias no build.gradle e no proguard
// 3) implementar cookie storage criar extensões em cima de Cookie
// 4) Definir as configurações do client (HttpClient) forecast proxima aula

// +------------------------------------------------------------------+
// | 1) INTERFACE PARA DEFINIR MECANISMOS QUE IRÁ LIMPAR OS COOKIES   |
// +------------------------------------------------------------------+
interface ClearableCookiesStorageType : CookiesStorage {
    /** METODO PARA REMOVER TODOS OS COOKIES SALVOS EM MEMORIA */
    suspend fun clear()
}

// +-----------------------------------------------------------------------+
// | 2) LOCAL ONDE GUARDAREMOS OS COOKIES ATE QUE SEJAM LIMPADOS/REMOVIDOS |
// +-----------------------------------------------------------------------+
class ClearableCookiesStorage : ClearableCookiesStorageType {
    private val cookies: MutableList<Cookie> = mutableListOf()
    private val mutex = Mutex()

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie): Unit = mutex.withLock {
        if (cookie.name.isBlank()) return

        cookies.removeAll { it.name == cookie.name && it.matches(requestUrl) }
        cookies.add(cookie.fillDefaults(requestUrl))
    }

    override fun close() = Unit

    override suspend fun get(requestUrl: Url): List<Cookie> = mutex.withLock {
        return cookies.filter { it.matches(requestUrl) }
    }

    // METODO USADO EM APLICATION PARA LIMPAR OS COOKIES SEMPRE QUE NECESSÁRIO
    override suspend fun clear() = mutex.withLock {
        cookies.clear()
    }
}

// +------------------------------------------------------------------+
// | 3) EXTENSÕES EM CIMA DE COOKIE PARA FACILITAR NOSSA VIDA         |
// +------------------------------------------------------------------+
fun Cookie.matches(requestUrl: Url): Boolean {
    return domain == requestUrl.host
}

fun Cookie.fillDefaults(requestUrl: Url): Cookie {
    var result = this

    if (result.path?.startsWith("/") != true) {
        result = result.copy(path = requestUrl.encodedPath)
    }

    if (result.domain.isNullOrBlank()) {
        result = result.copy(domain = requestUrl.host)
    }

    return result
}

// +---------------------------------------------------------------------+
// | 4) CONFIGURACÃO USADA PARA APLICAR NO HTTP-CLIENT QUANDO FOR CRIADO |
// +---------------------------------------------------------------------+
data class ClientConfig(
    /** UM DOS NOSSOS AMBIENTES QUE CRIAMOS ACIMA */
    val environment: Environment,
    /** VOCE QUE DEFINE. PODE SER "MOBILE", "WEB" OU ALGUM OUTRO IDENTIFICADOR QUE VC MESMO DEFINE */
    var userAgent: String,
    /** LOCAL ONDE SALVAMOS OS COOKIES DURANTE O USO DO CLIENT*/
    val cookiesStorage: ClearableCookiesStorageType = ClearableCookiesStorage()
)