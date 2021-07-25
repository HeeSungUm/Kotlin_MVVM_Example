package com.heesungum.kotlin_mvvm_example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRvAdapter (val contactItemClick : (Contact) -> Unit, val contactItemLongClick : (Contact) -> Unit)
    : RecyclerView.Adapter<ContactRvAdapter.ViewHolder>() {
    private var contacts: List<Contact> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val initialTv = itemView.findViewById<TextView>(R.id.item_tv_initial)
        val nameTv = itemView.findViewById<TextView>(R.id.item_tv_name)
        val numberTv = itemView.findViewById<TextView>(R.id.item_tv_number)

        fun bind (contact: Contact){
            nameTv.text = contact.name
            numberTv.text = contact.number
            initialTv.text = contact.initial.toString()

            itemView.setOnClickListener {
                contactItemClick(contact)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(contact)
                true
            }
        }
    }
    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}