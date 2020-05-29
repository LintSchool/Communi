package com.lintschool.communi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel(var id: Int, var email: String, var password: String,var token:String ,
                var image:String,var firstName:String,var lastName:String ) : Parcelable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (id != other.id) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (token != other.token) return false
        if (image != other.image) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + token.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }


}