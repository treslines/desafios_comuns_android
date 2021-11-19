package com.progdeelite.dca.environment

// 1) Definindo os ambientes de desenvolvimento
// 2) Casos de uso, detalhando cada propriedade
// 3) Forcast próxima aula Configurações do Client (HttpClient)

// +--------------------------------------------------------------------+
// | DEFINICÃO DO AMBIENTE QUE SERA USADO EM SEUS CLIENTS E REQUISICÕES |
// +--------------------------------------------------------------------+
enum class Environment(
    val host: String,
    val port: String = "",
    val useSSL: Boolean = true,
    val certificatePinningHashes: List<String> = emptyList(),
) {

    // +--------------------------------------------------------------------+
    // | DEFINICÃO DOS AMBIENTES                                            |
    // +--------------------------------------------------------------------+
    PROD(
        host = "programadordeelite.com.br",
        certificatePinningHashes = listOf(
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
        )
    ),

    STAGE(
        host = "state.programadordeelite.com.br",
        certificatePinningHashes = listOf(
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
        )
    ),

    DEV(
        host = "dev.programadordeelite.com.br",
        certificatePinningHashes = listOf(
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
        )
    ),

    TEST(
        host = "test.programadordeelite.com.br",
        certificatePinningHashes = listOf(
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
            "sha256/rE/SEU_HASH_DE_PINNING",
        )
    ),
    // +--------------------------------------------------------------------+
    // | AMBIENTES LOCAIS PARA DESENVOLVIMENTO (PRISM, DEMO, BACKEND_LOCAL) |
    // +--------------------------------------------------------------------+
    LOCAL_PRISM(
        host = "localhost",
        port = "4010",
        useSSL = false,
    ),

    LOCAL_BACKEND(
        host = "localhost",
        port = "8080",
        useSSL = false,
    ),

    DEMO(
        host = "demo",
    );

    // +--------------------------------------------------------------------+
    // | METODO AUXILIAR PARA SABER SE DEVEMOS USAR HEADERS ESPECIFICOS     |
    // +--------------------------------------------------------------------+
    fun isBackendMocked(): Boolean = when (this) {
        LOCAL_BACKEND -> true
        else -> false
    }

    // +--------------------------------------------------------------------+
    // | METODO AUXILIAR PARA SABER QUAL PROTOCOLO DEVEMO PREFIXAR          |
    // +--------------------------------------------------------------------+
    private fun baseUrl(): String = "${if (useSSL) "https://" else "http://"}$host:$port"


    // +--------------------------------------------------------------------+
    // | MONTA A URL BASE DE CADA SERVIDOR OU ENDPOINT                      |
    // +--------------------------------------------------------------------+
    fun mobileBaseUrl(): String = when (this) {
        LOCAL_PRISM -> baseUrl()
        else -> "${baseUrl()}/mobile" // OU QUALQUER OUTRA COISA QUE SUA EMPRESA DEFINA
    }

    // +--------------------------------------------------------------------+
    // | FORNECE A CREDENCIAIS DE AUTENTICACÃO USADAS NO AMBIENTES          |
    // +--------------------------------------------------------------------+
    fun basicAuthentication(): BasicAuthentication? = when (this) {
        // USE SUAS PROPRIAS CREDENCIAIS AQUI. APENAS PARA FINS DIDÁTICOS ASSIM
        STAGE -> BasicAuthentication(username = "testUser", password = "testUserStage")
        DEV -> BasicAuthentication(username = "testUser", password = "testUserDev")
        else -> null
    }

    // +---------------------------------------------------------------------------------+
    // | PARA SABER SE PRECISAMOS CONFIAR NO SERVIDOR BACKEND (DURANTE DESENVOLVIMENTO)  |
    // +---------------------------------------------------------------------------------+
    fun ignoreServerTrust(): Boolean = when (this) {
        TEST -> true
        else -> false
    }

    // +-------------------------------------------------------------------+
    // | PARA SABER SE PODEMOS DEBUGAR (DURANTE DESENVOLVIMENTO)           |
    // +-------------------------------------------------------------------+
    fun hasDebugIdentifier(): Boolean = when (this) {
        PROD -> false
        else -> true
    }
}

// +------------------------------------------------------------------+
// | CREDENDICAIS DE AUTENTICACÃO QUE USAREMOS NOS AMBIENTES          |
// +------------------------------------------------------------------+
data class BasicAuthentication(val username: String, val password: String)