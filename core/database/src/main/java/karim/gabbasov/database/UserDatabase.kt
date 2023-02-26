package karim.gabbasov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun user(): UserDao

    companion object {

        fun build(
            context: Context,
            databaseName: String = "online_shop.db"
        ): UserDatabase {
            return Room
                .databaseBuilder(context, UserDatabase::class.java, databaseName)
                .build()
        }
    }
}
