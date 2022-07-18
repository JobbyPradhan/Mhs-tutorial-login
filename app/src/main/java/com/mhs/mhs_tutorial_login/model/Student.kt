package com.mhs.mhs_tutorial_login.model

data class Student(
    val id :Int ?= null,
    val name:String = "",
    val desc :String = "",
    val profile:String =""
)