package karim.gabbasov.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.feature_api.features.SignInFeatureApi
import karim.gabbasov.model.data.user.UserDomain
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    val catalogFeatureApi: CatalogFeatureApi,
    val profileFeatureApi: ProfileFeatureApi,
    val signInFeatureApi: SignInFeatureApi
) : ViewModel() {

    val user = userRepository.currentUser
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserDomain("", "", "")
        )

    fun updateUserAvatar(uri: String) = viewModelScope.launch {
        user.value?.let {
            userRepository.updateUser(
                it.copy(
                    imageUri = uri
                )
            )
        }
    }

    fun logOut() = viewModelScope.launch {
        userRepository.logOut()
    }
}
