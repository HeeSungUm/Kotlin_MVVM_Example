package com.heesungum.kotlin_mvvm_example

import android.app.Application
import androidx.lifecycle.LiveData

class ContactViewModel (application : Application) {

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return  this.contacts
    }

    fun insert(contact: Contact){
        repository.insert(contact)
    }

    fun delete(contact: Contact){
        repository.delete(contact)
    }

}