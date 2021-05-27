package com.heesungum.kotlin_mvvm_example

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,


    @ColumnInfo(name = "name")
    var name: String,


    @ColumnInfo(name = "number")
    var number: String,


    @ColumnInfo(name = "initial")
    var initial : Char
){
    constructor() : this(null, "", "", '\u0000')
}
