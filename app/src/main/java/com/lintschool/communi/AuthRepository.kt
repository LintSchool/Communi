package com.lintschool.communi

interface AuthRepository {

    fun attemptLogin(email: String, password: String)

    fun attemptRegistration(email:String,password: String,firstName:String,lastName:String)

    fun checkPreviousAuthUser()

    fun saveAuthedUserToPref(userModel: UserModel)

    fun isTokenAvailable():Boolean
}