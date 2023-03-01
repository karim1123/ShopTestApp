package karim.gabbasov.data

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import karim.gabbasov.data.repository.auth.AuthRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

private const val AUTH_KEY = "token"
private const val PREF_NAME = "OnlineShop"

class AuthRepositoryImplTest {

    private val mockkContext = mockk<Context>()
    private val mockkSharedPref = mockk<SharedPreferences>()
    private val mockkSharedPrefEditor = mockk<SharedPreferences.Editor>()
    private lateinit var authRepository: AuthRepositoryImpl

    @Before
    fun setUp() {
        every { mockkContext.getSharedPreferences(PREF_NAME, any()) } returns mockkSharedPref
        every { mockkSharedPref.edit() } returns mockkSharedPrefEditor

        authRepository = AuthRepositoryImpl(mockkContext)
    }

    @Test
    fun `isAuthorize should return false when token not exist`() {
        every { mockkSharedPref.getString(AUTH_KEY, any()) } returns null

        val result = authRepository.isAuthorize()

        assertFalse(result)
    }

    @Test
    fun `isAuthorize should return true when token exist`() {
        every { mockkSharedPref.getString(AUTH_KEY, any()) } returns "token"

        val result = authRepository.isAuthorize()

        assertTrue(result)
    }

    @Test
    fun `getToken token when token exist return token`() {
        val token = "token"

        every { mockkSharedPref.getString(AUTH_KEY, any()) } returns token
        val result = authRepository.getToken()

        assertEquals(token, result)
    }

    @Test
    fun `getToken token when token not exist return null`() {
        every { mockkSharedPref.getString(AUTH_KEY, any()) } returns null

        val result = authRepository.getToken()

        assertNull(result)
    }

    @Test
    fun `authorize add token`() {
        val name = "name"
        every { mockkSharedPrefEditor.putString(AUTH_KEY, any()) } returns mockkSharedPrefEditor
        every { mockkSharedPrefEditor.commit() } returns true

        authRepository.authorize(name)

        verify(exactly = 1) { mockkSharedPrefEditor.putString(AUTH_KEY, name) }
        verify(exactly = 1) { mockkSharedPrefEditor.commit() }
    }

    @Test
    fun `unAuthorize remove token`() {
        every { mockkSharedPrefEditor.remove(AUTH_KEY) } returns mockkSharedPrefEditor
        every { mockkSharedPrefEditor.commit() } returns true

        authRepository.unAuthorize()

        verify(exactly = 1) { mockkSharedPrefEditor.remove(AUTH_KEY) }
        verify(exactly = 1) { mockkSharedPrefEditor.commit() }
    }
}
