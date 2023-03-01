package karim.gabbasov.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class UserDaoTest {

    private lateinit var db: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createBd() {
        db = Room.inMemoryDatabaseBuilder(
            context = InstrumentationRegistry.getInstrumentation().context,
            klass = UserDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
            .also {
                userDao = it.user()
            }
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun insertUserAndGet() = runTest {
        val user = UserEntity(
            "firstName",
            "mail@example.com",
            "lastName",
            null
        )
        userDao.insert(user)
        val loaded = userDao.retrieveUser(user.firstName).first()
        assertEquals(loaded?.firstName, user.firstName)
        assertEquals(loaded?.email, user.email)
        assertEquals(loaded?.lastName, user.lastName)
        assertEquals(loaded?.imageUri, user.imageUri)
    }

    @Test
    fun updateUserAndGet() = runTest {
        val user = UserEntity(
            "firstName",
            "mail@example.com",
            "lastName",
            null
        )
        userDao.insert(user)
        val loaded = userDao.retrieveUser(user.firstName).first()
        assertEquals(loaded?.firstName, user.firstName)
        assertEquals(loaded?.email, user.email)
        assertEquals(loaded?.lastName, user.lastName)
        assertEquals(loaded?.imageUri, user.imageUri)

        val updatedUser = loaded?.copy(email = "updated@example.com", imageUri = "testUri")
        updatedUser?.let { userDao.update(it) }

        val loadedUpdated = userDao.retrieveUser(user.firstName).first()
        assertEquals(loadedUpdated?.firstName, updatedUser?.firstName)
        assertEquals(loadedUpdated?.email, updatedUser?.email)
        assertEquals(loadedUpdated?.lastName, updatedUser?.lastName)
        assertEquals(loadedUpdated?.imageUri, updatedUser?.imageUri)
    }

    @Test
    fun retrieveNonExistentUser() = runTest {
        val loaded = userDao.retrieveUser("nonexistent").first()
        assertNull(loaded)
    }
}
