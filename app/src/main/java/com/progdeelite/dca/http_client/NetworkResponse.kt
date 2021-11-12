package com.progdeelite.dca.http_client

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

/** EXEMPLO: EXCECÃO GENERICA DE EXCECÃO QUE SUA API LANCA DE ACORDO COM CONTRATO DA MESMA */
class ApiException(val nameId: String, val httpStatusCode: Int? = null, val body: String? = null)

/**
 *  TIPOS DE EXCECÕES QUE SUA API LANCA DE ACORDO COM O CONTRATO DA MESMA - MAPPING
 *  VAMOS PRECISAR DISSO PARA MAPEAR OS ERROS QUE REDE A SEGUIR...
 */
sealed class AppException {
    // DE ACORDO COM A ESPECIFICAçÃO DA API, EXISTEM ESSAS EXCECÕES
    object ServiceTimeout : AppException()
    object ServiceCanceled : AppException()
    object ServiceOffline : AppException()

    companion object {
        private val apiExceptions: List<AppException> = listOf(
            ServiceTimeout,
            ServiceCanceled,
            ServiceOffline
        )

        // METODO QUE MAPEA EXCECÕES DA API PARA AS EXCECÕES DO SEU APLICATIVO
        fun toType(id: String): AppException? =
            apiExceptions.firstOrNull { id == it::class.simpleName }
    }
}

/** CLASSE QUE MAPEA AS EXCECÕES DA API, PARA QUE O APP NÃO DÊ UM CRASH EM PRODUCÃO */
sealed class NetworkError {

    object Unknown : NetworkError()
    object Offline : NetworkError()
    object Timeout : NetworkError()
    object Canceled : NetworkError()

    companion object {

        /** CHAME ESTE METODO NO RETORNO DA SUA API */
        fun parse(httpStatusCode: Int, body: String?): NetworkError {
            Timber.d("Parsing: $httpStatusCode | $body")
            body?.let { bodyString ->
                val apiException = try {
                    Json.decodeFromString<ApiException>(bodyString)
                } catch (ignore: SerializationException) {
                    return Unknown
                }

                return when (AppException.toType(apiException.nameId)) {
                    AppException.ServiceOffline -> Offline
                    AppException.ServiceTimeout -> Timeout
                    AppException.ServiceCanceled -> Canceled
                    else -> Unknown
                }
            }
            return Unknown
        }
    }
}

/** CLASE QUE MAPEA RESPOSTAS DA API DE FORMA GENERICA */
data class NetworkResponse<T>( // PAYLOAD GENERICO QUE CADA ENDPOINT ESPECIFICA (VIDE DEFINICÃO SUA API)
    val payload: T? = null,
    val error: NetworkError = NetworkError.Unknown
) {
    companion object {
        /** CHAME ESTE METODO NO RETORNO DA SUA API QUANDO SUCESSO */
        fun <T> success(payload: T) = NetworkResponse(payload = payload)

        /** CHAME ESTE METODO NO RETORNO DA SUA API CASO OCORRA ALGUM ERRO */
        fun <T> error(httpStatusCode: Int, body: String?) =
            NetworkResponse<T>(
                error = NetworkError.parse(
                    httpStatusCode,
                    body
                )
            ) // SEM PAYLOAD (VIDE DEFAULT ARGUMENT), MAS COM ERRO JA FOI PARCIADO >> TOPEZEIRA!!!
    }
}


