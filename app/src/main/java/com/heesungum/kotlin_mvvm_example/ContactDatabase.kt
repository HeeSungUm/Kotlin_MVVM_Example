package com.heesungum.kotlin_mvvm_example

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object{
        private var INSTANCE : ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase?{
            if (INSTANCE == null){
                synchronized(ContactDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, "contact")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}