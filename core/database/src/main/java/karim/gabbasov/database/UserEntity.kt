package karim.gabbasov.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import karim.gabbasov.model.data.UserDomain

@Entity
data class UserEntity(
    @PrimaryKey val firstName: String,
    val email: String,
    val lastName: String
) {
    companion object {
        fun UserEntity.toUserDomain(): UserDomain =
            UserDomain(
                email = email,
                firstName = firstName,
                lastName = lastName
            )

        fun UserDomain.toUserEntity(): UserEntity =
            UserEntity(
                email = email,
                firstName = firstName,
                lastName = lastName
            )
    }
}
