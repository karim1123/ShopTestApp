package karim.gabbasov.data.repository.user

import karim.gabbasov.model.data.user.UserDomain

interface UserRepository {

    fun getUser(firstName: String): UserDomain?

    suspend fun insertUser(user: UserDomain): UserApiResponse
}
