package karim.gabbasov.data.repository.user

import karim.gabbasov.model.data.UserDomain

interface UserRepository {

    fun getUser(firstName: String): UserDomain?

    suspend fun insertUser(user: UserDomain): UserApiResponse
}
