package com.mvvmlogin

class PersonRepository {
    fun login(email: String, password: String): Boolean {
        return (email != "" && password != "")
    }
}