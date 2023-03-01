package karim.gabbasov.data.repository.auth

interface AuthRepository {

    fun isAuthorize(): Boolean

    fun authorize(name: String)

    fun getToken(): String?

    fun unAuthorize()
}
