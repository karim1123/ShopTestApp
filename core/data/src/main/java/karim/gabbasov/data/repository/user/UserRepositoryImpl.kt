package karim.gabbasov.data.repository.user

import karim.gabbasov.data.repository.auth.AuthRepository
import karim.gabbasov.database.UserDao
import karim.gabbasov.database.UserEntity.Companion.toUserDomain
import karim.gabbasov.database.UserEntity.Companion.toUserEntity
import karim.gabbasov.model.data.user.UserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val auth: AuthRepository,
    private val userDao: UserDao
) : UserRepository {

    override val currentUser: Flow<UserDomain?>
        get() = getFlowOfCurrentUser()

    private suspend fun isUserExist(firstName: String): Boolean {
        return userDao.retrieveUser(firstName)
            .map { it != null }
            .firstOrNull() ?: false
    }

    private fun getFlowOfCurrentUser(): Flow<UserDomain?> = flow {
        val token = auth.getToken()
        if (!token.isNullOrEmpty()) {
            userDao.retrieveUser(token).collect {
                emit(it?.toUserDomain())
            }
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: UserDomain) {
        userDao.update(user.toUserEntity())
    }

    override suspend fun logOut() {
        auth.unAuthorize()
    }

    override suspend fun insertUser(user: UserDomain): UserApiResponse {
        return if (isUserExist(firstName = user.firstName)) {
            UserApiResponse.UserAlreadyExist
        } else {
            userDao.insert(user.toUserEntity())
            auth.authorize(user.firstName)
            UserApiResponse.Success
        }
    }

    override suspend fun authorize(firstName: String): Boolean {
        if (isUserExist(firstName)) {
            auth.authorize(firstName)
            return true
        }
        return false
    }
}

sealed class UserApiResponse {
    object Success : UserApiResponse()
    object UserAlreadyExist : UserApiResponse()
}
