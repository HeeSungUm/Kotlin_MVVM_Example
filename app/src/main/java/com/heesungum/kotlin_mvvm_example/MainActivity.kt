package com.heesungum.kotlin_mvvm_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val adapter = ContactRvAdapter({contact ->  }, {contact -> deleteDialog(contact)  })

        val lm = LinearLayoutManager(this)
        main_rv.adapter = adapter
        main_rv.layoutManager = lm
        main_rv.setHasFixedSize(true)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, {
            adapter.setContacts(it)
        })

        main_btn_add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") {_, _ ->}
            .setPositiveButton("YES") {_, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
}