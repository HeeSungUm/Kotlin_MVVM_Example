package com.heesungum.kotlin_mvvm_example

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

class ContactRepository(application : Application) {

    private val contactDatabase : ContactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao : ContactDao = contactDatabase.contactDao()
    private val contacts : LiveData<List<Contact>> = contactDao.getAll()

    fun getAll() : LiveData<List<Contact>> = contacts

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable { //Room DB를 메인쓰레드에서 접근하면 크래쉬가 발생함
                contactDao.insert(contact) })
            thread.start()
        } catch (e : Exception) {}
    }

    fun delete(contact: Contact){
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact) })
            thread.start()
        } catch (e : Exception) {}
    }
}