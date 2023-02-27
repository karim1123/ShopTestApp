package karim.gabbasov.data.repository.user

import karim.gabbasov.database.UserDao
import karim.gabbasov.database.UserEntity.Companion.toUserDomain
import karim.gabbasov.database.UserEntity.Companion.toUserEntity
import karim.gabbasov.model.data.user.UserDomain
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getUser(firstName: String): UserDomain? {
        return userDao.retrieveUser(firstName)?.toUserDomain()
    }

    override suspend fun insertUser(user: UserDomain): UserApiResponse {
        return if (getUser(firstName = user.firstName) != null) {
            UserApiResponse.UserAlreadyExist
        } else {
            userDao.insert(user.toUserEntity())
            UserApiResponse.Success
        }
    }
}

sealed class UserApiResponse {
    object Success : UserApiResponse()
    object UserAlreadyExist : UserApiResponse()
}
