package karim.gabbasov.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.data.repository.user.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindUserRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository
}
