package karim.gabbasov.data.repository.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val AUTH_KEY = "token"
private const val PREF_NAME = "OnlineShop"

internal class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext val appContext: Context
) : AuthRepository {
    private val sharedPref = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override fun isAuthorize(): Boolean {
        return !sharedPref.getString(AUTH_KEY, null).isNullOrEmpty()
    }

    override fun authorize(name: String) {
        editor.apply {
            putString(AUTH_KEY, name)
            commit()
        }
    }

    override fun getToken(): String? {
        return sharedPref.getString(AUTH_KEY, null)
    }

    override fun unAuthorize() {
        editor.apply {
            remove(AUTH_KEY)
            commit()
        }
    }
}
