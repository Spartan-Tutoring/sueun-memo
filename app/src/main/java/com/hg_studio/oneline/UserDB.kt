package com.hg_studio.oneline

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version=1)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao
}