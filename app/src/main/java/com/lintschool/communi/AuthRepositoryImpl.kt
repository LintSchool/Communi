package com.lintschool.communi

import android.content.SharedPreferences

class AuthRepositoryImpl(
    val apiAuthService: ApiAuthService,
    val sharedPreferences: SharedPreferences,
    val sharedPrefsEditor: SharedPreferences.Editor
) :AuthRepository{


    override fun attemptLogin(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun attemptRegistration(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        TODO("Not yet implemented")
    }

    override fun checkPreviousAuthUser() {
        TODO("Not yet implemented")
    }

    override fun saveAuthedUserToPref(userModel: UserModel) {
        TODO("Not yet implemented")
    }

    override fun isTokenAvailable(): Boolean {
        TODO("Not yet implemented")
    }


}