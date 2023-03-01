package karim.gabbasov.data.repository.user

import karim.gabbasov.model.data.user.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val currentUser: Flow<UserDomain?>

    suspend fun authorize(firstName: String): Boolean

    suspend fun updateUser(user: UserDomain)

    suspend fun logOut()

    suspend fun insertUser(user: UserDomain): UserApiResponse
}
